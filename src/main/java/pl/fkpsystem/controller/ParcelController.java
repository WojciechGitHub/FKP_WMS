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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("parcel")
public class ParcelController {

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private OrderedProductRepository orderedProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private VolunteerProductRepository volunteerProductRepository;

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


    @GetMapping("/remove/{parcelId}")
    private String deleteParcel(@PathVariable long parcelId) {
        Parcel parcel = parcelRepository.getOne(parcelId);
        parcelRepository.delete(parcel);
        return "redirect:/parcel/parcelList";
    }

    @RequestMapping("/parcelsToSpend")
    public String parcels() {
        return "parcel/parcelsToSpend";
    }

    @RequestMapping("/receive/{parcelId}")
    public String addParcel(@PathVariable long parcelId, Model model) {
        Parcel parcel = parcelRepository.getOne(parcelId);
        model.addAttribute("products", parcel.getOrderedProducts());
        return "orderedProduct/received";
    }

    @GetMapping("/receive/{parcelId}/findByBarcode")
    public String findProductByBarcode() {
        return "orderedProduct/find";
    }

    @PostMapping("/receive/{parcelId}/findByBarcode")
    public String fondedProductByBarcode(HttpServletRequest request, @PathVariable long parcelId, Model model) {
        String code = request.getParameter("code");
        OrderedProduct orderedProduct = orderedProductRepository.findOrderedProductByBarcodeAndParcelId(parcelId, code);
        if (orderedProduct != null) {
            model.addAttribute("orderedProduct", orderedProduct);
            return "orderedProduct/found";
        }
        return "orderedProduct/find";
    }
//

    @GetMapping("/receive/{parcelId}/findByName")
    public String findProductByName(Model model, @PathVariable long parcelId) {
        model.addAttribute("orderedProductList", orderedProductRepository.findAllByParcelId(parcelId));
        return "orderedProduct/findByName";
    }

    @PostMapping("/receive/{parcelId}/findByName")
    public String fondedProductByName(HttpServletRequest request, @PathVariable long parcelId, Model model) {

        long id = Long.parseLong(request.getParameter("productId"));
        OrderedProduct orderedProduct = orderedProductRepository.getOne(id);
        model.addAttribute("orderedProduct", orderedProduct);
        return "orderedProduct/found";

    }

    //
    @PostMapping("/receive/{parcelId}/addQuantity/{productId}")
    public String addQuantity(HttpServletRequest request, @PathVariable long productId) {
        int quantity = Integer.parseInt(request.getParameter("quantityToAdd"));
        OrderedProduct orderedProduct = orderedProductRepository.getOne(productId);
        Product product = orderedProduct.getProduct();
        orderedProduct.setReceivedQuantity(orderedProduct.getReceivedQuantity() + quantity);
        product.setOrderAmount(product.getOrderAmount() + quantity);
        orderedProductRepository.save(orderedProduct);
        productRepository.save(product);
        return "redirect:/parcel/receive/{parcelId}";
    }


    @RequestMapping("/spend/{parcelId}")
    public String volunteerList(@PathVariable long parcelId, Model model) {
        Set<Volunteer> volunteerList = volunteerRepository.findVolunteersByParcel(parcelId);
        model.addAttribute("volunteerList", volunteerList);
        return "orderedProduct/volunteerList";
    }

    @RequestMapping("/spend/{parcelId}/volunteer/{volunteerId}")
    public String volunteerProductList(@PathVariable long parcelId, @PathVariable long volunteerId, Model model) {
        List<VolunteerProduct> volunteerProductList = volunteerProductRepository.findVolunteerProductsByParcelIdAndVolunteerId(parcelId, volunteerId);
        model.addAttribute("volunteerProductList", volunteerProductList);
        model.addAttribute("volunteer", volunteerRepository.getOne(volunteerId));
        return "orderedProduct/volunteerProductList";
    }

    @GetMapping("/spend/{parcelId}/volunteer/{volunteerId}/product/{volunteerProductId}")
    public String volunteerProductSpendQuantity(@PathVariable long volunteerProductId, Model model) {
        Product product = volunteerProductRepository.getOne(volunteerProductId).getOrderedProduct().getProduct();
        model.addAttribute("product", product);
        return "orderedProduct/productSpend";
    }

    @PostMapping("/spend/{parcelId}/volunteer/{volunteerId}/product/{volunteerProductId}")
    public String volunteerProductSpendSaveQuantity(@PathVariable long volunteerId, @PathVariable long parcelId, @PathVariable long volunteerProductId, Model model, @RequestParam int quantityToSpend) {

        VolunteerProduct volunteerProduct = volunteerProductRepository.getOne(volunteerProductId);
        OrderedProduct orderedProduct = orderedProductRepository.getOne(volunteerProduct.getOrderedProduct().getId());
        Product product = volunteerProduct.getOrderedProduct().getProduct();

        volunteerProduct.setDistendedQuantity(volunteerProduct.getDistendedQuantity() + quantityToSpend);
        orderedProduct.setDistendedQuantity(orderedProduct.getDistendedQuantity() + quantityToSpend);
        product.setOrderAmount(product.getOrderAmount() - quantityToSpend);
        volunteerProductRepository.save(volunteerProduct);
        orderedProductRepository.save(orderedProduct);
        productRepository.save(product);
        return "redirect:/parcel/spend/" + parcelId + "/volunteer/" + volunteerId;
    }

}
