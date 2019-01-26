package pl.fkpsystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.fkpsystem.model.Volunteer;
import pl.fkpsystem.repository.VolunteerRepository;

public class VolunteerConverter implements Converter<String, Volunteer> {

    @Autowired
    VolunteerRepository volunteerRepository;

    @Override
    public Volunteer convert(String s) {
        return volunteerRepository.getOne(Long.parseLong(s));
    }

}
