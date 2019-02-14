package pl.fkpsystem.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.fkpsystem.model.Barcode;
import pl.fkpsystem.repository.BarcodeRepository;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BarcodeServiceTest {

    private BarcodeService barcodeService;

    @Mock
    private BarcodeRepository barcodeRepository;

    @Before
    public void setUp(){
        barcodeService=new BarcodeService( barcodeRepository);
    }

    @Test
    public void findExistingBarcode() {
        //given
        Barcode barcode=new Barcode();
        barcode.setCode("123");
        org.mockito.Mockito.when(barcodeRepository.findByCode(barcode.getCode())).thenReturn(barcode);
        //when
        Barcode result=barcodeService.findExistingBarcode(barcode);
        //then
        assertEquals("123",result.getCode());
    }

    @Test
    public void assignExistingBarcodeToNewProduct() {
    }


}