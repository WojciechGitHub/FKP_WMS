package pl.fkpsystem.FKP_WMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.fkpsystem.FKP_WMS.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product  JOIN barcode on product.id=barcode.product_id where barcode.code=?1", nativeQuery = true)
    Product findProductByBarcode(String string);

}
