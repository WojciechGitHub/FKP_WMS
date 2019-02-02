package pl.fkpsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.fkpsystem.repository.ProductRepository;

@Controller
@RequestMapping("volunteer")
@Secured({"ROLE_USER","ROLE_ADMIN"})

public class VolunteerController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/productList")
    String productList(Model model) {
        model.addAttribute("productList", productRepository.findAll());
        return "volunteer/productList";
    }
}
