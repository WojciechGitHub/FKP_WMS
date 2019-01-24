package pl.fkpsystem.FKP_WMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.fkpsystem.FKP_WMS.model.OrderedProduct;
import pl.fkpsystem.FKP_WMS.model.Parcel;

import javax.validation.Valid;

@Controller
@RequestMapping("volunteerProduct")
public class VolunteerProductController {


  /*  @GetMapping("/add/{parcelId}")
    public String addOrderedProduct(Model model, @PathVariable long parcelId) {
        model.addAttribute("orderedProduct", new OrderedProduct());
        Parcel parcel=parcelRepository.getOne(parcelId);
        model.addAttribute("parcelProducts",parcel.getOrderedProducts());
        return "orderedProduct/orderedProductForm";
    }

    @PostMapping("/add/{parcelId}")
    private String saveNewOrderedProduct(@Valid OrderedProduct orderedProduct, BindingResult bindingResult, @PathVariable long parcelId, Model model) {
        if (bindingResult.hasErrors()) {
            return "orderedProduct/orderedProductForm";
        }

        Parcel parcel=parcelRepository.getOne(parcelId);
        orderedProduct.setParcel(parcel);
        orderedProductRepository.save(orderedProduct);
        model.addAttribute("parcelProducts",parcel.getOrderedProducts());
        return "orderedProduct/orderedProductForm";
    }*/

}
