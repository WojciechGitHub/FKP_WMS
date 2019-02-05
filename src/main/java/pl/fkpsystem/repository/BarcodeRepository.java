package pl.fkpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.fkpsystem.model.Barcode;
@Repository
public interface BarcodeRepository extends JpaRepository<Barcode, Long> {

    public Barcode findByCode(String code);

}
