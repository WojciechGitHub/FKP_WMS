package pl.fkpsystem.FKP_WMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.fkpsystem.FKP_WMS.model.Parcel;

public interface ParcelRepository extends JpaRepository<Parcel, Long> {

}
