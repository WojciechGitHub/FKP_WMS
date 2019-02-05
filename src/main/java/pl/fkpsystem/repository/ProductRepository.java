package pl.fkpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.fkpsystem.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product  JOIN barcode on product.id=barcode.product_id where barcode.code=?1", nativeQuery = true)
    Product findProductByBarcode(String string);

    List<Product> findAllByType(String type);

}
