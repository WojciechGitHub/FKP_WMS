package pl.fkpsystem.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"orderedProducts","volunteerProducts"})
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "parcel", cascade = CascadeType.ALL)
    private List<OrderedProduct> orderedProducts=new ArrayList<>();

    @OneToMany(mappedBy = "parcel",cascade = CascadeType.ALL)
    private List<VolunteerProduct> volunteerProducts=new ArrayList<>();


}
