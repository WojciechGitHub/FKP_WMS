package pl.fkpsystem.FKP_WMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.fkpsystem.FKP_WMS.model.OrderedProduct;
import pl.fkpsystem.FKP_WMS.model.Parcel;
import pl.fkpsystem.FKP_WMS.model.Product;
import pl.fkpsystem.FKP_WMS.model.VolunteerProduct;
import pl.fkpsystem.FKP_WMS.repository.BarcodeRepository;
import pl.fkpsystem.FKP_WMS.repository.OrderedProductRepository;
import pl.fkpsystem.FKP_WMS.repository.ParcelRepository;
import pl.fkpsystem.FKP_WMS.repository.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("parcel")
public class ParcelController {

    @Autowired
    private ParcelRepository parcelRepository;

    @Autowired
    private OrderedProductRepository orderedProductRepository;

    @Autowired
    private ProductRepository productRepository;

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
        OrderedProduct orderedProduct=orderedProductRepository.findOrderedProductByBarcodeAndParcelId(parcelId,code);
        if (orderedProduct != null) {
            model.addAttribute("orderedProduct", orderedProduct);

            //return "redirect:/parcel/spend/{parcelId}/findByBarcode/addQuantity";
            return "orderedProduct/found";
        }

        return "orderedProduct/find";
    }

    //////////

    @PostMapping("/receive/{parcelId}/addQuantity/{productId}")
    public String addQuantity(HttpServletRequest request, @PathVariable long productId) {

        int quantity=Integer.parseInt(request.getParameter("quantityToAdd"));
        OrderedProduct orderedProduct = orderedProductRepository.getOne(productId);
        Product product=orderedProduct.getProduct();
        orderedProduct.setReceivedQuantity(orderedProduct.getReceivedQuantity()+quantity);
        product.setOrderAmount(product.getOrderAmount()+quantity);
        orderedProductRepository.save(orderedProduct);
        productRepository.save(product);

        return "redirect:/parcel/receive/{parcelId}";
    }

    ////////



}
