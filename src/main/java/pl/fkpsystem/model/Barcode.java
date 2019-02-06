package pl.fkpsystem.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class Barcode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String code;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
}
