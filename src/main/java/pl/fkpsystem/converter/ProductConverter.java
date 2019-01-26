package pl.fkpsystem.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.fkpsystem.model.Product;
import pl.fkpsystem.repository.ProductRepository;

@Component
public class ProductConverter implements Converter<String, Product> {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product convert(String s) {
        return productRepository.getOne(Long.parseLong(s));
    }
}
