package pl.fkpsystem.FKP_WMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.fkpsystem.FKP_WMS.model.OrderedProduct;
import pl.fkpsystem.FKP_WMS.model.VolunteerProduct;

public interface VolunteerProductRepository extends JpaRepository<VolunteerProduct, Long>{

    //SELECT * FROM volunteer_product WHERE volunteer_product.volunteer_id=1 and volunteer_product.parcel_id=1 and volunteer_product.ordered_product_id=1
    @Query(value = "SELECT * FROM volunteer_product WHERE volunteer_product.volunteer_id=?1 and volunteer_product.parcel_id=?2 and volunteer_product.ordered_product_id=3", nativeQuery = true)
    VolunteerProduct findVolunteerProductByVolunteerIdAndParcelIdAndOrderedProductId(Long volunteerId, Long parcelId, Long orderedProductId);

}
