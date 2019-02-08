package pl.fkpsystem.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.fkpsystem.model.OrderedProduct;
import pl.fkpsystem.model.Parcel;
import pl.fkpsystem.model.Volunteer;
import pl.fkpsystem.model.VolunteerProduct;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@DataJpaTest
public class VolunteerProductRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private VolunteerProductRepository volunteerProductRepository;

    @Test
    public void shouldFindVolunteerProductByVolunteerIdAndParcelIdAndOrderedProductId() {
        //given
        Volunteer volunteer=new Volunteer();
        volunteer.setId(3);
        testEntityManager.persist(volunteer);

        Parcel parcel=new Parcel();
        parcel.setId(5);
        testEntityManager.persist(parcel);

        OrderedProduct orderedProduct=new OrderedProduct();
        orderedProduct.setId(7);
        testEntityManager.persist(orderedProduct);

        VolunteerProduct volunteerProduct=new VolunteerProduct();
        volunteerProduct.setVolunteer(volunteer);
        volunteerProduct.setParcel(parcel);
        volunteerProduct.setOrderedProduct(orderedProduct);
        testEntityManager.persist(volunteerProduct);

        //when
        VolunteerProduct result1=volunteerProductRepository.findVolunteerProductByVolunteerIdAndParcelIdAndOrderedProductId(3l,5l,7l);
        //then
        assertEquals(volunteerProduct,result1);
    }

    @Test
    public void findVolunteerProductsByParcelIdAndVolunteerId() {
    }

    @Test
    public void shouldFindAllByParcelId() {
        //given
        Parcel parcel=new Parcel();
        parcel.setId(5);
        testEntityManager.persist(parcel);

        VolunteerProduct volunteerProduct1=new VolunteerProduct();
        VolunteerProduct volunteerProduct2=new VolunteerProduct();
        volunteerProduct1.setParcel(parcel);
        volunteerProduct2.setParcel(parcel);
        testEntityManager.persist(volunteerProduct1);
        testEntityManager.persist(volunteerProduct2);

        //when
        List<VolunteerProduct> list=volunteerProductRepository.findAllByParcelId(5l);

        //then
        assertThat(list).containsExactly(volunteerProduct1,volunteerProduct2);
    }
}