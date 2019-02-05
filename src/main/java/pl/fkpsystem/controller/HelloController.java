package pl.fkpsystem.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured({"ROLE_REGISTERED","ROLE_USER","ROLE_ADMIN"})
public class HelloController {

    @RequestMapping("/")
    public String hello(){
        return "index";
    }

}
