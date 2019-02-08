package pl.fkpsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.fkpsystem.service.AdminService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin")
@Secured("ROLE_ADMIN")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("/volunteerList")
    public String volunteerList(Model model) {
        model.addAttribute("volunteerList", adminService.getAllVolunteers());
        return "admin/volunteerList";
    }

    @RequestMapping("/volunteer/{volunteerId}/removeRights/{roleId}")
    public String removeRight(@PathVariable long volunteerId, @PathVariable long roleId) {
        adminService.deleteVolunteerRight(volunteerId, roleId);
        return "redirect:/admin/volunteerList";
    }

    @GetMapping("/addRights/{volunteerId}")
    public String removeRight(Model model,@PathVariable long volunteerId) {
        model.addAttribute("volunteer",adminService.getVolunteer(volunteerId));
        model.addAttribute("roles",adminService.getAllRoles());
        return "admin/addRightsForm";
    }

    @PostMapping("/addRights/{volunteerId}")
    public String removeRight(Model model, @PathVariable long volunteerId, HttpServletRequest request) {
        adminService.addRight(volunteerId,request);
        return "redirect:/admin/volunteerList";
    }

    @GetMapping("/delete/{volunteerId}")
    public String removeVolunteer(Model model,@PathVariable long volunteerId) {
        model.addAttribute("volunteer",adminService.getVolunteer(volunteerId));
        model.addAttribute("volunteerId",volunteerId);
        return "admin/deleteConfirmation";
    }

    @GetMapping("/delete/{volunteerId}/confirmed")
    public String deleteVolunteer(Model model,@PathVariable long volunteerId) {
        adminService.deleteVolunteer(volunteerId);
        return "redirect:/admin/volunteerList";
    }

}