package pl.fkpsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.fkpsystem.model.Barcode;
import pl.fkpsystem.model.Product;
import pl.fkpsystem.repository.BarcodeRepository;
import pl.fkpsystem.repository.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    BarcodeRepository barcodeRepository;

    public List<Product> allKittyLitters() {
        return productRepository.findAllByType("kittyLitter");
    }

    public List<Product> allFeeds() {
        return productRepository.findAllByType("feed");
    }

    public List<Barcode> allBarcodes() {
        return barcodeRepository.findAll();
    }

    public void saveProduct(Product product, String type) {
        product.setType(type); // postarac sie ustawic jako input hidden w form, zeby nie ustawiac tego tutaj
        productRepository.save(product);
    }

    public void saveProductMethod(Product product, String type) {
        if (product.getBarcodes().size() != 0) {
            Barcode barcode = product.getBarcodes().get(0);
            barcode.setProduct(product);
        }
        saveProduct(product, type);
    }

    public Product findProductById(long productId) {
        return productRepository.getOne(productId);
    }

    public Product findProductById(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("productId"));
        return productRepository.getOne(id);
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    public Barcode findBarcodeByCode(HttpServletRequest request) {
        String code = request.getParameter("code");
        return barcodeRepository.findByCode(code);
    }

    public Product findProductByCode(HttpServletRequest request) {
        String code = request.getParameter("code");
        return productRepository.findProductByBarcode(code);
    }

    public List<Product> allProducts() {
        return productRepository.findAll();
    }

    public void saveProductWithChangedQuantity(HttpSession session, HttpServletRequest request, int operation){     //przejrzec jeszczce raz metode zeby sesji sie pozbyc
        Product product = (Product) session.getAttribute("foundedProduct");
        int quantity = Integer.parseInt(request.getParameter("quantityToAdd"));
        int q = product.getReserveAmount() + (quantity*operation);
        product.setReserveAmount(q);
        productRepository.save(product);
    }

}
