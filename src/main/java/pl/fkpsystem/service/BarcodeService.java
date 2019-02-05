package pl.fkpsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.fkpsystem.model.Barcode;
import pl.fkpsystem.repository.BarcodeRepository;

@Service
public class BarcodeService {

    @Autowired
    BarcodeRepository barcodeRepository;

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
