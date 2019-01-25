package pl.fkpsystem.FKP_WMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.fkpsystem.FKP_WMS.model.Barcode;
import pl.fkpsystem.FKP_WMS.model.Product;
import pl.fkpsystem.FKP_WMS.repository.BarcodeRepository;
import pl.fkpsystem.FKP_WMS.repository.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("product")
@SessionAttributes({"foundedProduct"})
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    BarcodeRepository barcodeRepository;

    @RequestMapping("/productList")
    public String kittyLitterList(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "product/productList";
    }

    @ModelAttribute("barcodeList")
    public List<Barcode> barcodeList() {
        return barcodeRepository.findAll();
    }


    @GetMapping("/add")
    public String addKittyLitter(Model model) {
        model.addAttribute("product", new Product());
        return "product/productForm";
    }

    @PostMapping("/add")
    private String saveNewProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product/productForm";
        }
        Barcode barcode = product.getBarcodes().get(0);
        barcode.setProduct(product);

        productRepository.save(product);
        return "redirect:/product/productList";
    }

    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product/productForm";
    }

    @PostMapping("/update/{id}")
    private String saveUpdatedKittyLitter(@Valid Product product, @PathVariable long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product/productForm";
        }
        productRepository.save(product);
        return "redirect:/product/productList";
    }

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
    public String findProduct() {
        return "product/find";
    }

    @PostMapping("/reserve/get")
    private String foundedproduct(HttpServletRequest request, Model model) {
        String code = request.getParameter("code");
        if (barcodeRepository.findByCode(code) != null) {
            Product foundedProduct = productRepository.findProductByBarcode(code);
            model.addAttribute("foundedProduct", foundedProduct);
            return "product/found";
        }

        return "redirect:/product/find";
    }

    @PostMapping("/reserve/get/quantity")
    private String savePositivQuantity(HttpSession session,HttpServletRequest request, Model model) {
        Product product=(Product) session.getAttribute("foundedProduct");
        int quantity=Integer.parseInt(request.getParameter("quantityToAdd"));
        int q=product.getReserveAmount()+quantity;
        product.setReserveAmount(q);
        productRepository.save(product);
        return "redirect:/product/reserve";
    }


}
