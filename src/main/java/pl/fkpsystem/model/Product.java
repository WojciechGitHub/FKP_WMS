package pl.fkpsystem.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"barcodes"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Barcode> barcodes = new ArrayList<>();

    @NotBlank
    private String producer;
    @NotBlank
    private String description;
    @NumberFormat
    private double grammage;
    @NotBlank
    private String unitOfGrammage;

    private String type; //karma czy zwir

    @NotBlank
    private String sort; //sucha czy mokra/drewniany czy bentonit

    //@NotBlank
    private String age; //kitten, adult, senior

    private int orderAmount;

    private int reserveAmount;

    //jako lista uwag
    private String reservationByVolunteer;

    //jako lista uwag
    private String remarks;

    // ew stworzyć jako encje
    // smak (lista)
    //dodac pole boolean veterinary
    // jak wterynaryjna lista przypadłości

    @Override
    public String toString() {
        return producer + " " + description + " " + grammage + " " + unitOfGrammage;
    }

    
}
