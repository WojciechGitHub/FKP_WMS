package pl.fkpsystem.FKP_WMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.fkpsystem.FKP_WMS.model.Product;
import pl.fkpsystem.FKP_WMS.model.Volunteer;
import pl.fkpsystem.FKP_WMS.model.VolunteerProduct;

import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    @Query(value = "SELECT * FROM volunteer JOIN volunteer_product ON volunteer.id=volunteer_product.volunteer_id WHERE volunteer_product.parcel_id=?1", nativeQuery = true)
    List<Volunteer> findVolunteersByParcel(Long id);

}
