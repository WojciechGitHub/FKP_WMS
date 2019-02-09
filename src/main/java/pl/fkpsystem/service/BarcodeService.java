package pl.fkpsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import pl.fkpsystem.model.Barcode;
import pl.fkpsystem.repository.BarcodeRepository;

@Service
@RequiredArgsConstructor
public class BarcodeService {

    private final BarcodeRepository barcodeRepository;

    public Barcode findExistingBarcode(Barcode barcode) {
        return barcodeRepository.findByCode(barcode.getCode());
    }

    public void assignExistingBarcodeToNewProduct(Barcode barcode) {
        Barcode existingBarcode = findExistingBarcode(barcode);
        existingBarcode.setProduct(barcode.getProduct());
        barcodeRepository.save(existingBarcode);
    }

    public void saveBarcode(Barcode barcode) {
        barcodeRepository.save(barcode);
    }


}
