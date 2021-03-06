package pl.fkpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.fkpsystem.model.Parcel;
@Repository
public interface ParcelRepository extends JpaRepository<Parcel, Long> {

}
