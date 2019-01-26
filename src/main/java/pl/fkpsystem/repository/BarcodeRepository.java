package pl.fkpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.fkpsystem.model.Barcode;

public interface BarcodeRepository extends JpaRepository<Barcode, Long> {

    public Barcode findByCode(String code);

}
