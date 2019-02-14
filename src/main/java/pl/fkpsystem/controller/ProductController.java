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
import pl.fkpsystem.service.ProductService;

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
    ProductService productService;

    @RequestMapping("/productList")
    public String Products(Model model) {
        model.addAttribute("kittyLitters", productService.allKittyLitters());
        model.addAttribute("feeds", productService.allFeeds());
        return "product/productList";
    }

    @ModelAttribute("barcodeList")
    public List<Barcode> barcodeList() {
        return productService.allBarcodes();
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
        productService.saveProductMethod(product, "kittyLitter");
        return "redirect:/product/productList";
    }

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
        productService.saveProductMethod(product, "feed");
        return "redirect:/product/productList";
    }

    @GetMapping("/updateKittyLitter/{id}")
    public String updateProduct(@PathVariable long id, Model model) {
        model.addAttribute("product", productService.findProductById(id));
        return "product/kittyLitterForm";
    }

    @PostMapping("/updateKittyLitter/{id}")
    public String saveUpdatedKittyLitter(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product/kittyLitterForm";
        }
        productService.saveProduct(product, "kittyLitter");
        return "redirect:/product/productList";
    }

    @GetMapping("/updateFeed/{id}")
    public String updateFeed(@PathVariable long id, Model model) {
        model.addAttribute("product", productService.findProductById(id));
        return "product/feedForm";
    }

    @PostMapping("/updateFeed/{id}")
    public String saveUpdatedFeed(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product/feedForm";
        }
        productService.saveProduct(product, "feed");
        return "redirect:/product/productList";
    }

    @GetMapping("/delete/{id}")
    public String deleteKittyLitter(@PathVariable long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("product",productService.findProductById(id));
        return "product/productDelete";
    }

    @GetMapping("/delete/{id}/confirmed")
    public String deleteKittyLitterConfirmed(@PathVariable long id) {
        productService.deleteProduct(id);
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
        if (productService.findBarcodeByCode(request) != null) {
            model.addAttribute("foundedProduct", productService.findProductByCode(request));
            return "product/foundAdd";
        }
        return "redirect:/product/reserve/get";
    }

    @GetMapping("/reserve/addByName")
    public String findProductByNameToAdd(Model model) {
        model.addAttribute("productList", productService.allProducts());
        return "product/findByName";
    }

    @PostMapping("/reserve/addByName")
    public String foundedProductByNameToAdd(HttpServletRequest request, Model model) {
        model.addAttribute("foundedProduct", productService.findProductById(request));
        return "product/foundAdd";
    }

    @PostMapping("/reserve/get/quantity")
    public String savePositivQuantity(HttpSession session, HttpServletRequest request, Model model) {
        productService.saveProductWithChangedQuantity(session, request, 1);
        return "redirect:/product/reserve";
    }

    @GetMapping("/reserve/spend")
    public String findProductToSpend() {
        return "product/findSpend";
    }

    @PostMapping("/reserve/spend")
    public String foundedProductToSpend(HttpServletRequest request, Model model) {
        if (productService.findBarcodeByCode(request) != null) {
            model.addAttribute("foundedProduct", productService.findProductByCode(request));
            return "product/foundSpend";
        }
        return "redirect:/product/reserve/spend";
    }

    @GetMapping("/reserve/spendByName")
    public String findProductByNameToSpend(Model model) {
        model.addAttribute("productList", productService.allProducts());
        return "product/findByNameToSpend";
    }

    @PostMapping("/reserve/spendByName")
    public String foundedProductByNameToSpend(HttpServletRequest request, Model model) {
        model.addAttribute("foundedProduct", productService.findProductById(request));
        return "product/foundSpend";
    }

    @PostMapping("/reserve/spend/quantity")
    public String saveNegativQuantityt(HttpSession session, HttpServletRequest request, Model model) {
        productService.saveProductWithChangedQuantity(session, request, -1);
        return "redirect:/product/reserve";
    }

}
