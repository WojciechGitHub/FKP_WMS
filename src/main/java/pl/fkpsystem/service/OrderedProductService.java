package pl.fkpsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.fkpsystem.model.*;
import pl.fkpsystem.repository.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderedProductService {
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

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public List<OrderedProduct> orderedProductsFromParcel(long parcelId) {
        return parcelRepository.getOne(parcelId).getOrderedProducts();
    }

    public void saveOrderedProduct(OrderedProduct orderedProduct, long parcelId) {
        Parcel parcel = parcelRepository.getOne(parcelId);
        for (OrderedProduct o : parcel.getOrderedProducts()) {      //przerobic na lambde
            if (o.getProduct() == orderedProduct.getProduct()) {
                orderedProduct = o;
            }
        }
        orderedProduct.setParcel(parcel);
        orderedProductRepository.save(orderedProduct);
    }

    public void deleteVolunteerProductFromParcel(long orderProductId, long parcelId) {
        VolunteerProduct volunteerProduct = volunteerProductRepository.getOne(orderProductId);
        OrderedProduct orderedProduct = orderedProductRepository.getOne(volunteerProduct.getOrderedProduct().getId());
        orderedProduct.setOrderedQuantity(orderedProduct.getOrderedQuantity() - volunteerProduct.getOrderedQuantity());
        orderedProductRepository.save(orderedProduct);
        volunteerProductRepository.delete(volunteerProduct);
    }

    public void deleteOrderedProductFromParcel(long orderedProductId, long parcelId) {
        OrderedProduct orderedProduct = orderedProductRepository.getOne(orderedProductId);
        orderedProductRepository.delete(orderedProduct);
    }

    public List<OrderedProduct> getOrderedProductsFromParcel(long parcelId) {
        return parcelRepository.getOne(parcelId).getOrderedProducts();
    }


    public List<Volunteer> getAllVolunteers() {   //czy ta metode lepiej dac do VolunteerService?
        return volunteerRepository.findAll();    // i dac @Autowired w controllerze Orderproduct na Volunteerservice czy
    }                                            //czy lepiej trzymac wszsytko w jednym serwisie do controllera?


    public Map<Volunteer, List<VolunteerProduct>> mapOfVolunteersAndVolunteerProducts(long parcelId) {
        List<VolunteerProduct> volunteerProductList = volunteerProductRepository.findAllByParcelId(parcelId);
        Map<Volunteer, List<VolunteerProduct>> unsortedMap = volunteerProductList.stream().collect(Collectors.groupingBy(VolunteerProduct::getVolunteer));
        Map<Volunteer, List<VolunteerProduct>> volunteerListMap = new LinkedHashMap<>();
        unsortedMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey((v1, v2) -> v1.getName().compareToIgnoreCase(v2.getName())))
                .forEach(c -> volunteerListMap.put(c.getKey(), c.getValue()));
        return volunteerListMap;
    }

    public void saveVolunteerProduct(VolunteerProduct volunteerProduct, long parcelId) {
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
    }

    public void saveParcel(long parcelId){
        Parcel parcel = parcelRepository.getOne(parcelId);
        if (parcel.getOrderedProducts().size() != 0) {                      //wrzuca ilosc produktu w zamowieniu - sprobowac jakos uładnic
            for (OrderedProduct o : parcel.getOrderedProducts()) {
                int quantity = 0;
                for (VolunteerProduct v : o.getVolunteerProducts()) {
                    quantity += v.getOrderedQuantity();
                }
                o.setOrderedQuantity(quantity);
            }
        }
        parcelRepository.save(parcel);
    }

}
