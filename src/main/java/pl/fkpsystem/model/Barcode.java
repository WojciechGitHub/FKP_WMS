package pl.fkpsystem.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Barcode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String code;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
}
