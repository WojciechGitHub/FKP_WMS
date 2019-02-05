package pl.fkpsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.fkpsystem.model.*;
import pl.fkpsystem.service.OrderedProductService;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("orderedProduct")
@Secured("ROLE_ADMIN")
public class OrderedProductController {

    @Autowired
    OrderedProductService orderedProductService;

    @ModelAttribute("products")
    public List<Product> productList() {
        return orderedProductService.findAllProducts();
    }

    @GetMapping("/add/{parcelId}")
    public String addOrderedProduct(Model model, @PathVariable long parcelId) {
        model.addAttribute("orderedProduct", new OrderedProduct());
        model.addAttribute("parcelProducts", orderedProductService.orderedProductsFromParcel(parcelId));
        return "orderedProduct/orderedProductForm";
    }

    @PostMapping("/add/{parcelId}")
    public String saveNewOrderedProduct(@Valid OrderedProduct orderedProduct, BindingResult bindingResult, @PathVariable long parcelId, Model model) {
        if (bindingResult.hasErrors()) {
            return "orderedProduct/orderedProductForm";
        }
        orderedProductService.saveOrderedProduct(orderedProduct, parcelId);
        model.addAttribute("parcelProducts", orderedProductService.orderedProductsFromParcel(parcelId));
        return "redirect:/orderedProduct/add/" + parcelId;
    }

    @GetMapping("/add/{parcelId}/volunteerProducts/remove/{orderProductId}")
    public String removeVolunteerProduct(@PathVariable long orderProductId, @PathVariable long parcelId) {
        orderedProductService.deleteVolunteerProductFromParcel(orderProductId, parcelId);
        return "redirect:/orderedProduct/add/" + parcelId + "/volunteerProducts";
    }

    @GetMapping("/add/{parcelId}/removeProduct/{orderedProductId}")
    public String removeOrderedProduct(@PathVariable long orderedProductId, @PathVariable long parcelId) {
        orderedProductService.deleteOrderedProductFromParcel(orderedProductId, parcelId);
        return "redirect:/orderedProduct/add/" + parcelId;
    }

    @GetMapping("/add/{parcelId}/volunteerProducts")
    public String addOrderedProductToVolunteer(Model model, @PathVariable long parcelId) {
        model.addAttribute("volunteerProduct", new VolunteerProduct());
        model.addAttribute("parcelProducts", orderedProductService.getOrderedProductsFromParcel(parcelId));
        model.addAttribute("volunteers", orderedProductService.getAllVolunteers());
        model.addAttribute("volunteerListMap", orderedProductService.mapOfVolunteersAndVolunteerProducts(parcelId));
        return "volunteerProduct/volunteerProductForm";
    }

    @PostMapping("/add/{parcelId}/volunteerProducts")
    public String saveOrderedProductToVolunteer(@Valid VolunteerProduct volunteerProduct, BindingResult bindingResult, @PathVariable long parcelId, Model model) {
        if (bindingResult.hasErrors()) {
            return "volunteerProduct/volunteerProductForm";
        }
        orderedProductService.saveVolunteerProduct(volunteerProduct, parcelId);
        model.addAttribute("volunteerListMap", orderedProductService.mapOfVolunteersAndVolunteerProducts(parcelId));
        orderedProductService.saveParcel(parcelId);
        return "redirect:/orderedProduct/add/" + parcelId + "/volunteerProducts";
    }

}