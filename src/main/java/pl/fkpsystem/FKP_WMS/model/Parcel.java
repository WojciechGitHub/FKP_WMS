package pl.fkpsystem.FKP_WMS.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
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
