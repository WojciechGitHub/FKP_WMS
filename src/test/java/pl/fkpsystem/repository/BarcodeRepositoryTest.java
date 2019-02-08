package pl.fkpsystem.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.fkpsystem.model.Barcode;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@DataJpaTest
public class BarcodeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private BarcodeRepository barcodeRepository;

    @Test
    public void shouldFindByCode() {
        //given
        Barcode barcode1=new Barcode();
        barcode1.setCode("123");
        testEntityManager.persist(barcode1);
        //when
        Barcode result=barcodeRepository.findByCode("123");
        //then
        assertEquals(barcode1.getCode(), result.getCode());
    }
}