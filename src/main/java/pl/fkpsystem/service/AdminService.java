package pl.fkpsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.fkpsystem.model.Role;
import pl.fkpsystem.model.Volunteer;
import pl.fkpsystem.repository.RoleRepository;
import pl.fkpsystem.repository.VolunteerRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    VolunteerRepository volunteerRepository;

    @Autowired
    RoleRepository roleRepository;

    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void deleteVolunteerRight(long volunteerId, long rightId) {
        Volunteer volunteer = volunteerRepository.getOne(volunteerId);
        Role foundedRole = volunteer.getRoles().stream().filter(role -> role.getId() == rightId).findFirst().orElse(null);
        volunteer.getRoles().remove(foundedRole);
        volunteerRepository.save(volunteer);
    }

    public void addRight(long volunteerId, HttpServletRequest request) {
        long rightId = Long.parseLong(request.getParameter("rightId"));
        Volunteer volunteer = volunteerRepository.getOne(volunteerId);
        Role role = roleRepository.getOne(rightId);
        volunteer.getRoles().add(role);
        volunteerRepository.save(volunteer);
    }

    public Volunteer getVolunteer(long id) {
        return volunteerRepository.getOne(id);
    }

    public void deleteVolunteer(long volunteerId) {
        volunteerRepository.deleteById(volunteerId);
    }

}
