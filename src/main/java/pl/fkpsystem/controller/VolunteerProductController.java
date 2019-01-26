package pl.fkpsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
