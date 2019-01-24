package pl.fkpsystem.FKP_WMS.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class VolunteerProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ordered_product_id")
    private OrderedProduct orderedProduct;

    @ManyToOne
    @JoinColumn(name = "volunteer_id")
    private Volunteer volunteer;

    @ManyToOne
    @JoinColumn(name = "parcel_id")
    private Parcel parcel;

    private int orderedQuantity;
    private int distendedQuantity;

}
