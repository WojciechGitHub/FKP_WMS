package pl.fkpsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.fkpsystem.model.Barcode;
import pl.fkpsystem.model.Product;
import pl.fkpsystem.repository.BarcodeRepository;
import pl.fkpsystem.repository.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("product")
@SessionAttributes({"foundedProduct"})
@Secured("ROLE_ADMIN")
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    BarcodeRepository barcodeRepository;

    @RequestMapping("/productList")
    public String Products(Model model) {
        List<Product> kittyLitters = productRepository.findAllByType("kittyLitter");
        model.addAttribute("kittyLitters", kittyLitters);
        List<Product> feeds = productRepository.findAllByType("feed");
        model.addAttribute("feeds", feeds);
        return "product/productList";
    }

    @ModelAttribute("barcodeList")
    public List<Barcode> barcodeList() {
        return barcodeRepository.findAll();
    }

    @GetMapping("/addKittyLitter")
    public String addKittyLitter(Model model) {
        model.addAttribute("product", new Product());
        return "product/kittyLitterForm";
    }

    @PostMapping("/addKittyLitter")
    public String saveNewProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product/kittyLitterForm";
        }
        if (product.getBarcodes().size() != 0) {
            Barcode barcode = product.getBarcodes().get(0);
            barcode.setProduct(product);
        }
        product.setType("kittyLitter"); // postarac sie ustawic jako hidden w form
        productRepository.save(product);
        return "redirect:/product/productList";
    }

    ////
    @GetMapping("/addFeed")
    public String addFeed(Model model) {
        model.addAttribute("product", new Product());
        return "product/feedForm";
    }

    @PostMapping("/addFeed")
    public String saveNewFeed(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product/feedForm";
        }
        if (product.getBarcodes().size() != 0) {
            Barcode barcode = product.getBarcodes().get(0);
            barcode.setProduct(product);
        }
        product.setType("feed"); // postarac sie ustawic jako hidden w form
        productRepository.save(product);
        return "redirect:/product/productList";
    }
    ////

    @GetMapping("/updateKittyLitter/{id}")
    public String updateProduct(@PathVariable long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product/kittyLitterForm";
    }

    @PostMapping("/updateKittyLitter/{id}")
    public String saveUpdatedKittyLitter(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product/kittyLitterForm";
        }
        product.setType("kittyLitter");
        productRepository.save(product);
        return "redirect:/product/productList";
    }

    /////
    @GetMapping("/updateFeed/{id}")
    public String updateFeed(@PathVariable long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product/feedForm";
    }

    @PostMapping("/updateFeed/{id}")
    public String saveUpdatedFeed(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product/feedForm";
        }
        product.setType("feed");
        productRepository.save(product);
        return "redirect:/product/productList";
    }
    /////

    @GetMapping("/delete/{id}")
    public String deleteKittyLitter(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        return "product/productDelete";
    }

    @GetMapping("/delete/{id}/confirmed")
    public String deleteKittyLitterConfirmed(@PathVariable long id) {
        productRepository.deleteById(id);
        return "redirect:/product/productList";
    }


    @RequestMapping("/reserve")
    public String reserve() {
        return "product/reserve";
    }

    @GetMapping("/reserve/get")
    public String findProductToAdd() {
        return "product/findAdd";
    }

    @PostMapping("/reserve/get")
    public String foundedProductToAdd(HttpServletRequest request, Model model) {
        String code = request.getParameter("code");
        if (barcodeRepository.findByCode(code) != null) {
            Product foundedProduct = productRepository.findProductByBarcode(code);
            model.addAttribute("foundedProduct", foundedProduct);
            return "product/foundAdd";
        }
        return "redirect:/product/findAdd";
    }


    @GetMapping("/reserve/addByName")
    public String findProductByNameToAdd(Model model) {
        model.addAttribute("productList", productRepository.findAll());
        return "product/findByName";
    }

    @PostMapping("/reserve/addByName")
    public String foundedProductByNameToAdd(HttpServletRequest request, Model model) {
        long id = Long.parseLong(request.getParameter("productId"));
        Product foundedProduct = productRepository.getOne(id);
        model.addAttribute("foundedProduct", foundedProduct);
        return "product/foundAdd";
    }


    @PostMapping("/reserve/get/quantity")
    public String savePositivQuantity(HttpSession session, HttpServletRequest request, Model model) {
        Product product = (Product) session.getAttribute("foundedProduct");
        int quantity = Integer.parseInt(request.getParameter("quantityToAdd"));
        int q = product.getReserveAmount() + quantity;
        product.setReserveAmount(q);
        productRepository.save(product);
        return "redirect:/product/reserve";
    }


    @GetMapping("/reserve/spend")
    public String findProductToSpend() {
        return "product/findSpend";
    }

    @PostMapping("/reserve/spend")
    public String foundedProductToSpend(HttpServletRequest request, Model model) {
        String code = request.getParameter("code");
        if (barcodeRepository.findByCode(code) != null) {
            Product foundedProduct = productRepository.findProductByBarcode(code);
            model.addAttribute("foundedProduct", foundedProduct);
            return "product/foundSpend";
        }
        return "redirect:/product/findSpend";
    }

    //
    @GetMapping("/reserve/spendByName")
    public String findProductByNameToSpend(Model model) {
        model.addAttribute("productList", productRepository.findAll());
        return "product/findByNameToSpend";
    }

    @PostMapping("/reserve/spendByName")
    public String foundedProductByNameToSpend(HttpServletRequest request, Model model) {
        long id = Long.parseLong(request.getParameter("productId"));
        Product foundedProduct = productRepository.getOne(id);
        model.addAttribute("foundedProduct", foundedProduct);
        return "product/foundSpend";
    }

    //
    @PostMapping("/reserve/spend/quantity")
    public String saveNegativQuantityt(HttpSession session, HttpServletRequest request, Model model) {
        Product product = (Product) session.getAttribute("foundedProduct");
        int quantity = Integer.parseInt(request.getParameter("quantityToSpend"));
        int q = product.getReserveAmount() - quantity;
        product.setReserveAmount(q);
        productRepository.save(product);
        return "redirect:/product/reserve";
    }

}
