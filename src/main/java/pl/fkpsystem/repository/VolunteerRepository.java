package pl.fkpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.fkpsystem.model.Volunteer;

import java.util.List;
import java.util.Set;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    @Query(value = "SELECT * FROM volunteer JOIN volunteer_product ON volunteer.id=volunteer_product.volunteer_id WHERE volunteer_product.parcel_id=?1", nativeQuery = true)
    Set<Volunteer> findVolunteersByParcel(Long id);

}
