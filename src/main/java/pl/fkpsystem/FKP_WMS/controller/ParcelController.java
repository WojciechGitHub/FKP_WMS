package pl.fkpsystem.FKP_WMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.fkpsystem.FKP_WMS.model.OrderedProduct;
import pl.fkpsystem.FKP_WMS.model.Parcel;
import pl.fkpsystem.FKP_WMS.model.VolunteerProduct;
import pl.fkpsystem.FKP_WMS.repository.ParcelRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("parcel")
public class ParcelController {

    @Autowired
    private ParcelRepository parcelRepository;

    @ModelAttribute("parcels")
    public List<Parcel> parcelList() {
        return parcelRepository.findAll();
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
    private String saveNewParcel(@Valid Parcel parcel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "parcel/parcelForm";
        }
        parcelRepository.save(parcel);
        return "redirect:/parcel/parcelList";
    }

///

    @RequestMapping("/parcelsToSpend")
    public String parcels() {
        return "parcel/parcelsToSpend";
    }

    @RequestMapping("/spend/{parcelId}")
    public String addParcel(@PathVariable long parcelId, Model model) {
        Parcel parcel = parcelRepository.getOne(parcelId);
        model.addAttribute("products", parcel.getOrderedProducts());
        return "orderedProduct/received";
    }

    @GetMapping("/spend/{parcelId}/findByBarcode")
    public String findProductByBarcode() {
        return "product/find";
    }

    @PostMapping("/spend/{parcelId}/findByBarcode")
    public String fondedProductByBarcode(@PathVariable long parcelId) {
        Parcel parcel=parcelRepository.getOne(parcelId);

        return "product/find";
    }

}
