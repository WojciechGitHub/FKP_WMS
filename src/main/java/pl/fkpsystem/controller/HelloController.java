package pl.fkpsystem.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Secured("ROLE_REGISTERED")
public class HelloController {

    @RequestMapping("/")
    public String hello(){
        return "hello";
    }

}
