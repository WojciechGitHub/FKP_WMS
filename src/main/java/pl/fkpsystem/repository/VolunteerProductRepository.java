package pl.fkpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.fkpsystem.model.VolunteerProduct;

import java.util.List;

public interface VolunteerProductRepository extends JpaRepository<VolunteerProduct, Long>{

    @Query(value = "SELECT * FROM volunteer_product WHERE volunteer_product.volunteer_id=?1 and volunteer_product.parcel_id=?2 and volunteer_product.ordered_product_id=?3", nativeQuery = true)
    VolunteerProduct findVolunteerProductByVolunteerIdAndParcelIdAndOrderedProductId(Long volunteerId, Long parcelId, Long orderedProductId);

    @Query(value = "SELECT * FROM volunteer_product JOIN volunteer ON volunteer_product.volunteer_id=volunteer.id WHERE volunteer_product.parcel_id=?1 and volunteer_id=?2", nativeQuery = true)
    List<VolunteerProduct> findVolunteerProductsByParcelIdAndVolunteerId(Long parcelId, Long volunteerId);

    List<VolunteerProduct> findAllByParcelId(long id);

}
