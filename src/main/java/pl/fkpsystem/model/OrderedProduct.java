package pl.fkpsystem.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"volunteerProducts"})
public class OrderedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="parcel_id")
    private Parcel parcel;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "orderedProduct", cascade = CascadeType.ALL)
    private List<VolunteerProduct> volunteerProducts=new ArrayList<>();

    private int orderedQuantity;
    private int receivedQuantity;
    private int distendedQuantity;
}
