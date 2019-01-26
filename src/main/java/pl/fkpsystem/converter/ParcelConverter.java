package pl.fkpsystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.fkpsystem.model.Parcel;
import pl.fkpsystem.repository.ParcelRepository;

@Component
public class ParcelConverter implements Converter<String, Parcel> {

    @Autowired
    ParcelRepository parcelRepository;

    @Override
    public Parcel convert(String s) {
        return parcelRepository.getOne(Long.parseLong(s));
    }
}
