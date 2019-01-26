package pl.fkpsystem.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Barcode> barcodes=new ArrayList<>();


    private String description;

    //@NotBlank
    private String producer;



    private double grammage;
    //@NotBlank
    private String unitOfGrammage;
    //@NotBlank
    private String sort;

    private int orderAmount;

    private int reserveAmount;
    //private int sum=orderAmount+reserveAmount;
    private String reservationByVolunteer;
    private String remarks;



}
