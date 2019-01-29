package pl.fkpsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.fkpsystem.model.*;
import pl.fkpsystem.repository.*;
import pl.fkpsystem.model.*;
import pl.fkpsystem.repository.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("orderedProduct")
public class OrderedProductController {

    @Autowired
    OrderedProductRepository orderedProductRepository;

    @Autowired
    ParcelRepository parcelRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    VolunteerRepository volunteerRepository;

    @Autowired
    VolunteerProductRepository volunteerProductRepository;

    @ModelAttribute("products")
    public List<Product> productList() {
        return productRepository.findAll();
    }

    @GetMapping("/add/{parcelId}")
    public String addOrderedProduct(Model model, @PathVariable long parcelId) {
        model.addAttribute("orderedProduct", new OrderedProduct());
        Parcel parcel = parcelRepository.getOne(parcelId);
        model.addAttribute("parcelProducts", parcel.getOrderedProducts());
        return "orderedProduct/orderedProductForm";
    }

    @PostMapping("/add/{parcelId}")
    private String saveNewOrderedProduct(@Valid OrderedProduct orderedProduct, BindingResult bindingResult, @PathVariable long parcelId, Model model) {
        if (bindingResult.hasErrors()) {
            return "orderedProduct/orderedProductForm";
        }

        Parcel parcel = parcelRepository.getOne(parcelId);
        for (OrderedProduct o : parcel.getOrderedProducts()) {      //ogarnac bo cos srednio dziala jak mam dodany kod kreskowy od poczatku-fetch?
            if (o.getProduct() == orderedProduct.getProduct()) {
                orderedProduct = o;
            }
        }

        orderedProduct.setParcel(parcel);
        orderedProductRepository.save(orderedProduct);
        model.addAttribute("parcelProducts", parcel.getOrderedProducts());
        return "redirect:/orderedProduct/add/" + parcelId;
    }

    @GetMapping("/add/{parcelId}/volunteerProducts/remove/{orderProductId}")
    public String removeVolunteerProduct(@PathVariable long orderProductId, @PathVariable long parcelId) {
        VolunteerProduct volunteerProduct = volunteerProductRepository.getOne(orderProductId);
        OrderedProduct orderedProduct=orderedProductRepository.getOne(volunteerProduct.getOrderedProduct().getId());
        orderedProduct.setOrderedQuantity(orderedProduct.getOrderedQuantity()-volunteerProduct.getOrderedQuantity());
        orderedProductRepository.save(orderedProduct);
        volunteerProductRepository.delete(volunteerProduct);
        return "redirect:/orderedProduct/add/" + parcelId + "/volunteerProducts";
    }

    @GetMapping("/add/{parcelId}/removeProduct/{orderedProductId}")
    public String removeOrderedProduct(@PathVariable long orderedProductId, @PathVariable long parcelId) {
        OrderedProduct orderedProduct = orderedProductRepository.getOne(orderedProductId);
        orderedProductRepository.delete(orderedProduct);
        return "redirect:/orderedProduct/add/" + parcelId;
    }

    //tutaj dodam przypisywanie produktow do wolo
    //kwestia czy tej akcji nie umiescic w innym kontrolerze->VolunteerProductController?

    @GetMapping("/add/{parcelId}/volunteerProducts")
    public String addOrderedProductToVolunteer(Model model, @PathVariable long parcelId) {
        model.addAttribute("volunteerProduct", new VolunteerProduct());

        Parcel parcel = parcelRepository.getOne(parcelId);
        model.addAttribute("parcelProducts", parcel.getOrderedProducts());

        List<Volunteer> volunteers = volunteerRepository.findAll();
        model.addAttribute("volunteers", volunteers);

        Set<Volunteer> volunteersFromParcel = volunteerRepository.findVolunteersByParcel(parcelId);
        model.addAttribute("volunteersFromParcel", volunteersFromParcel);

        List<VolunteerProduct> volunteerProductList = volunteerProductRepository.findAllByParcelId(parcelId);
        Map<Volunteer, List<VolunteerProduct>> unsortedMap = volunteerProductList.stream().collect(Collectors.groupingBy(VolunteerProduct::getVolunteer));
        Map<Volunteer, List<VolunteerProduct>> volunteerListMap = new LinkedHashMap<>();
        unsortedMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey((v1, v2) -> v1.getName().compareToIgnoreCase(v2.getName())))
                .forEach(c -> volunteerListMap.put(c.getKey(), c.getValue()));
        model.addAttribute("volunteerListMap", volunteerListMap);

        return "volunteerProduct/volunteerProductForm";
    }


    @PostMapping("/add/{parcelId}/volunteerProducts")
    public String saveOrderedProductToVolunteer(@Valid VolunteerProduct volunteerProduct, BindingResult bindingResult, @PathVariable long parcelId, Model model) {
        if (bindingResult.hasErrors()) {
            return "volunteerProduct/volunteerProductForm";
        }

        long volunteerId = volunteerProduct.getVolunteer().getId();
        long orderedProductId = volunteerProduct.getOrderedProduct().getId();
        VolunteerProduct volunteerProductQuested = volunteerProductRepository.findVolunteerProductByVolunteerIdAndParcelIdAndOrderedProductId(volunteerId, parcelId, orderedProductId);
        if (volunteerProductQuested == null) {
            volunteerProduct.setParcel(parcelRepository.getOne(parcelId));
            volunteerProductRepository.save(volunteerProduct);
        } else {
            volunteerProductQuested.setOrderedQuantity(volunteerProduct.getOrderedQuantity());
            volunteerProductRepository.save(volunteerProductQuested);
        }


        List<VolunteerProduct> volunteerProductList = volunteerProductRepository.findAllByParcelId(parcelId);
        Map<Volunteer, List<VolunteerProduct>> unsortedMap = volunteerProductList.stream().collect(Collectors.groupingBy(VolunteerProduct::getVolunteer));
        Map<Volunteer, List<VolunteerProduct>> volunteerListMap = new LinkedHashMap<>();
        unsortedMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey((v1, v2) -> v1.getName().compareToIgnoreCase(v2.getName())))
                .forEach(c -> volunteerListMap.put(c.getKey(), c.getValue()));
        model.addAttribute("volunteerListMap", volunteerListMap);

        Parcel parcel = parcelRepository.getOne(parcelId);
        if (parcel.getOrderedProducts().size() != 0) {                      //swiete-wrzuca ilosc produktu w zamowieniu
            for (OrderedProduct o : parcel.getOrderedProducts()) {
                int quantity = 0;
                for (VolunteerProduct v : o.getVolunteerProducts()) {
                    quantity += v.getOrderedQuantity();
                }
                o.setOrderedQuantity(quantity);
            }
        }
        parcelRepository.save(parcel);

        return "redirect:/orderedProduct/add/" + parcelId + "/volunteerProducts";
    }


}