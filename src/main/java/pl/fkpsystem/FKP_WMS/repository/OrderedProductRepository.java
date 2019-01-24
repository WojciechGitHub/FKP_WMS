package pl.fkpsystem.FKP_WMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.fkpsystem.FKP_WMS.model.OrderedProduct;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Long> {
}
