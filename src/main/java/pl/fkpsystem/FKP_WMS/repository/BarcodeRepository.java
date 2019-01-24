package pl.fkpsystem.FKP_WMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.fkpsystem.FKP_WMS.model.Barcode;

public interface BarcodeRepository extends JpaRepository<Barcode, Long> {

    public Barcode findByCode(String code);

}
