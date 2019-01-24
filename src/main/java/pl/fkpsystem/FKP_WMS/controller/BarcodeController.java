package pl.fkpsystem.FKP_WMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.fkpsystem.FKP_WMS.model.Barcode;
import pl.fkpsystem.FKP_WMS.repository.BarcodeRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("barcode")
public class BarcodeController {

    @Autowired
    BarcodeRepository barcodeRepository;


    @GetMapping("/add/{product}")
    public String addBarcode(@PathVariable String product , Model model) {
        model.addAttribute("barcode", new Barcode());
        model.addAttribute("product",product);
        return "barcode/barcodeForm";
    }


    @PostMapping("/add/{product}")
    private String saveNewBarcode(@Valid Barcode barcode, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "barcode/barcodeForm";
        }

        if(barcodeRepository.findByCode(barcode.getCode())!=null){
           Barcode existingBarcode= barcodeRepository.findByCode(barcode.getCode());
           existingBarcode.setProduct(barcode.getProduct());
           barcodeRepository.save(existingBarcode);
           return "redirect:/product/productList";
        }


        barcodeRepository.save(barcode);
        return "redirect:/product/productList";
    }

}
