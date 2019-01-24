package pl.fkpsystem.FKP_WMS.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.fkpsystem.FKP_WMS.model.Barcode;
import pl.fkpsystem.FKP_WMS.repository.BarcodeRepository;

@Component
public class BarcodeConverter implements Converter<String, Barcode> {

    @Autowired
    BarcodeRepository barcodeRepository;

    @Override
    public Barcode convert(String s) {

        if (barcodeRepository.findByCode(s) != null) {
            return barcodeRepository.findByCode(s);
        }

        Barcode barcode = new Barcode();
        barcode.setCode(s);
        return barcode;
    }

}
