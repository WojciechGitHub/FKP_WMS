package pl.fkpsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.fkpsystem.model.*;
import pl.fkpsystem.service.ParcelService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("parcel")
@Secured("ROLE_ADMIN")
public class ParcelController {

    @Autowired
    ParcelService parcelService;

    @ModelAttribute("parcels")
    public List<Parcel> parcelList() {
        return parcelService.parcels();
    }

    @RequestMapping("/parcelList")
    public String parcelListView() {
        return "parcel/parcelList";
    }

    @GetMapping("/add")
    public String addParcel(Model model) {
        model.addAttribute("parcel", new Parcel());
        return "parcel/parcelForm";
    }

    @PostMapping("/add")
    public String saveNewParcel(@Valid Parcel parcel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "parcel/parcelForm";
        }
        parcelService.saveParcel(parcel);
        return "redirect:/parcel/parcelList";
    }

    @GetMapping("/deleteConfirmation/{parcelId}")
    public String deleteConfirmation(@PathVariable long parcelId, Model model) {
        model.addAttribute("parcelId", parcelId);
        return "parcel/deleteConfirmation";
    }

    @GetMapping("/deleteConfirmation/{parcelId}/remove")
    public String deleteParcel(@PathVariable long parcelId) {
        parcelService.deleteParcel(parcelId);
        return "redirect:/parcel/parcelList";
    }

    @RequestMapping("/parcelsToSpend")
    public String parcels() {
        return "parcel/parcelsToSpend";
    }

    @RequestMapping("/receive/{parcelId}")
    public String addParcel(@PathVariable long parcelId, Model model) {
        model.addAttribute("products", parcelService.getOrderedProductsFromParcel(parcelId));
        return "orderedProduct/received";
    }

    @GetMapping("/receive/{parcelId}/findByBarcode")
    public String findProductByBarcode() {
        return "orderedProduct/find";
    }


    @PostMapping("/receive/{parcelId}/findByBarcode")
    public String fondedProductByBarcode(HttpServletRequest request, @PathVariable long parcelId, Model model) {
        OrderedProduct orderedProduct = parcelService.findByParcelIdAndBarcode(request, parcelId);
        if (orderedProduct != null) {
            model.addAttribute("orderedProduct", orderedProduct);
            return "orderedProduct/found";
        }
        return "orderedProduct/find";
    }

    @GetMapping("/receive/{parcelId}/findByName")
    public String findProductByName(Model model, @PathVariable long parcelId) {
        model.addAttribute("orderedProductList", parcelService.getOrderedProductsFromParcel(parcelId));
        return "orderedProduct/findByName";
    }

    @PostMapping("/receive/{parcelId}/findByName")
    public String fondedProductByName(HttpServletRequest request, @PathVariable long parcelId, Model model) {
        model.addAttribute("orderedProduct", parcelService.findByIdFromRequest(request));
        return "orderedProduct/found";
    }

    @PostMapping("/receive/{parcelId}/addQuantity/{productId}")
    public String addQuantity(HttpServletRequest request, @PathVariable long productId) {
        parcelService.saveQuantityOfReceivedOrderedProduct(request, productId);
        return "redirect:/parcel/receive/{parcelId}";
    }

    @RequestMapping("/spend/{parcelId}")
    public String volunteerList(@PathVariable long parcelId, Model model) {
        model.addAttribute("volunteerList", parcelService.volunteersFromParcel(parcelId));
        return "orderedProduct/volunteerList";
    }

    @RequestMapping("/spend/{parcelId}/volunteer/{volunteerId}")
    public String volunteerProductList(@PathVariable long parcelId, @PathVariable long volunteerId, Model model) {
        model.addAttribute("volunteerProductList", parcelService.volunteersFromParcel(parcelId));
        model.addAttribute("volunteer", parcelService.findVolunteer(volunteerId));
        return "orderedProduct/volunteerProductList";
    }

    @GetMapping("/spend/{parcelId}/volunteer/{volunteerId}/product/{volunteerProductId}")
    public String volunteerProductSpendQuantity(@PathVariable long volunteerProductId, Model model) {
        model.addAttribute("product", parcelService.productOrderedByVolunteer(volunteerProductId));
        return "orderedProduct/productSpend";
    }

    @PostMapping("/spend/{parcelId}/volunteer/{volunteerId}/product/{volunteerProductId}")
    public String volunteerProductSpendSaveQuantity(@PathVariable long volunteerId, @PathVariable long parcelId, @PathVariable long volunteerProductId, @RequestParam int quantityToSpend) {
        parcelService.spendOrderedQuantityOfVolunteerProduct(volunteerProductId, quantityToSpend);
        return "redirect:/parcel/spend/" + parcelId + "/volunteer/" + volunteerId;
    }

}
