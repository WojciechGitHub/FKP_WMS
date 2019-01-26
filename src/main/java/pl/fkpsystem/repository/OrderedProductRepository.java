package pl.fkpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.fkpsystem.model.OrderedProduct;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Long> {


    @Query(value = "SELECT * FROM ordered_product JOIN barcode ON barcode.product_id=ordered_product.product_id WHERE ordered_product.parcel_id=?1 and barcode.code=?2", nativeQuery = true)
    OrderedProduct findOrderedProductByBarcodeAndParcelId(Long id, String code);
}
