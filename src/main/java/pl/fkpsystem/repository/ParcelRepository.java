package pl.fkpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.fkpsystem.model.Parcel;

public interface ParcelRepository extends JpaRepository<Parcel, Long> {

}
