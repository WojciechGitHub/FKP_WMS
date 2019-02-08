package pl.fkpsystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.fkpsystem.model.Product;
import pl.fkpsystem.model.Role;
import pl.fkpsystem.repository.ProductRepository;
import pl.fkpsystem.repository.RoleRepository;

@Component
public class RoleConverter implements Converter<String, Role> {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role convert(String s) {
        return roleRepository.getOne(Long.parseLong(s));
    }

}
