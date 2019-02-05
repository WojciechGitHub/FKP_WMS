package pl.fkpsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pl.fkpsystem.model.*;
import pl.fkpsystem.repository.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Service
public class ParcelService {
    @Autowired
    ParcelRepository parcelRepository;
    @Autowired
    OrderedProductRepository orderedProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    VolunteerRepository volunteerRepository;
    @Autowired
    VolunteerProductRepository volunteerProductRepository;

    public List<Parcel> parcels() {
        return parcelRepository.findAll();
    }

    public void saveParcel(Parcel parcel) {
        parcelRepository.save(parcel);
    }

    public void deleteParcel(long parcelId) {
        Parcel parcel = parcelRepository.getOne(parcelId);
        parcelRepository.delete(parcel);
    }

    public List<OrderedProduct> getOrderedProductsFromParcel(long parcelId) {
        Parcel parcel = parcelRepository.getOne(parcelId);
        return parcel.getOrderedProducts();
    }

    public OrderedProduct findByParcelIdAndBarcode(HttpServletRequest request, long parcelId) {
        String code = request.getParameter("code");
        return orderedProductRepository.findOrderedProductByBarcodeAndParcelId(parcelId, code);
    }

    public OrderedProduct findByIdFromRequest(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("productId"));
        return orderedProductRepository.getOne(id);
    }

    public void saveQuantityOfReceivedOrderedProduct(HttpServletRequest request, long productId) {
        int quantity = Integer.parseInt(request.getParameter("quantityToAdd"));
        OrderedProduct orderedProduct = orderedProductRepository.getOne(productId);
        Product product = orderedProduct.getProduct();
        orderedProduct.setReceivedQuantity(orderedProduct.getReceivedQuantity() + quantity);
        product.setOrderAmount(product.getOrderAmount() + quantity);
        orderedProductRepository.save(orderedProduct);
        productRepository.save(product);
    }

    public Set<Volunteer> volunteersFromParcel(long parcelId) {
        return volunteerRepository.findVolunteersByParcel(parcelId);
    }

    public List<VolunteerProduct> volunteerProductList(long parcelId, long volunteerId) {
        return volunteerProductRepository.findVolunteerProductsByParcelIdAndVolunteerId(parcelId, volunteerId);
    }

    public Volunteer findVolunteer(long volunteerId) {
        return volunteerRepository.getOne(volunteerId);
    }

    public Product productOrderedByVolunteer(long volunteerProductId) {
        return volunteerProductRepository.getOne(volunteerProductId).getOrderedProduct().getProduct();
    }

    public void spendOrderedQuantityOfVolunteerProduct(long volunteerProductId, int quantityToSpend){
        VolunteerProduct volunteerProduct = volunteerProductRepository.getOne(volunteerProductId);
        OrderedProduct orderedProduct = orderedProductRepository.getOne(volunteerProduct.getOrderedProduct().getId());
        Product product = volunteerProduct.getOrderedProduct().getProduct();

        volunteerProduct.setDistendedQuantity(volunteerProduct.getDistendedQuantity() + quantityToSpend);
        orderedProduct.setDistendedQuantity(orderedProduct.getDistendedQuantity() + quantityToSpend);
        product.setOrderAmount(product.getOrderAmount() - quantityToSpend);
        volunteerProductRepository.save(volunteerProduct);
        orderedProductRepository.save(orderedProduct);
        productRepository.save(product);
    }

}
