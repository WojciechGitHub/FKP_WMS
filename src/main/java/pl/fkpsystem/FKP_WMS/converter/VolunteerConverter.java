package pl.fkpsystem.FKP_WMS.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.fkpsystem.FKP_WMS.model.Product;
import pl.fkpsystem.FKP_WMS.model.Volunteer;
import pl.fkpsystem.FKP_WMS.repository.ProductRepository;
import pl.fkpsystem.FKP_WMS.repository.VolunteerRepository;

public class VolunteerConverter implements Converter<String, Volunteer> {

    @Autowired
    VolunteerRepository volunteerRepository;

    @Override
    public Volunteer convert(String s) {
        return volunteerRepository.getOne(Long.parseLong(s));
    }

}
