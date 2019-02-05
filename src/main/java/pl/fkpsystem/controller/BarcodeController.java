package pl.fkpsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.fkpsystem.model.Barcode;
import pl.fkpsystem.repository.BarcodeRepository;
import pl.fkpsystem.service.BarcodeService;

import javax.validation.Valid;

@Controller
@RequestMapping("barcode")
@Secured("ROLE_ADMIN")
public class BarcodeController {

    @Autowired
    BarcodeService barcodeService;

    @GetMapping("/add/{product}")
    public String addBarcode(@PathVariable String product , Model model) {
        model.addAttribute("barcode", new Barcode());
        model.addAttribute("product",product);
        return "barcode/barcodeForm";
    }

    @PostMapping("/add/{product}")
    public String saveNewBarcode(@Valid Barcode barcode, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "barcode/barcodeForm";
        }
        if(barcodeService.findExistingBarcode(barcode)!=null){
           barcodeService.assignExistingBarcodeToNewProduct(barcode);
           return "redirect:/product/productList";
        }
        barcodeService.saveBarcode(barcode);
        return "redirect:/product/productList";
    }

}
