package pl.fkpsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.fkpsystem.model.Volunteer;
import pl.fkpsystem.service.VolunteerService;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    VolunteerService volunteerService;

    @GetMapping("/login")
    public String login() {
        return "authorization/login";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("volunteer", new Volunteer());
        return "authorization/registration";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@Valid Volunteer volunteer, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Volunteer volunteerExists = volunteerService.findByVolunteerName(volunteer.getName());
        if (volunteerExists != null) {
            return "authorization/registration";
        }
        if (bindingResult.hasErrors()) {
            return "authorization/registration";
        } else {
            volunteerService.saveVolunteer(volunteer);

        }
        return "redirect:/login";
    }


}
