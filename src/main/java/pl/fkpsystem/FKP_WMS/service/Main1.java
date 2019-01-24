package pl.fkpsystem.FKP_WMS.service;

import pl.fkpsystem.FKP_WMS.model.Product;
import pl.fkpsystem.FKP_WMS.model.Volunteer;

import java.util.*;

public class Main1 {

    public static void main(String[] args) {

        Volunteer volunteer1 = new Volunteer();
        Volunteer volunteer2 = new Volunteer();
        Volunteer volunteer3 = new Volunteer();

        Product kittyLitter1 = new Product();
        Product kittyLitter2 = new Product();
        Product kittyLitter3 = new Product();
        kittyLitter1.setProducer("A");
        kittyLitter2.setProducer("B");
        kittyLitter3.setProducer("C");


        Map<Volunteer, Object> mainMap = new HashMap<>();
        Map<Product, Integer> subMap1 = new HashMap<>();
        Map<Product, Integer> subMap2 = new HashMap<>();
        Map<Product, Integer> subMap3 = new HashMap<>();

        subMap1.put(kittyLitter1, 2);
        subMap1.put(kittyLitter2, 3);
        mainMap.put(volunteer1, subMap1);

        subMap2.put(kittyLitter3, 2);
        mainMap.put(volunteer2, subMap2);

        subMap3.put(kittyLitter2, 2);
        mainMap.put(volunteer3, subMap3);


        Set<Product> volunteers = subMap1.keySet();

        for (Product v : volunteers) {
            System.out.println(v.getProducer());
        }


    }
}

