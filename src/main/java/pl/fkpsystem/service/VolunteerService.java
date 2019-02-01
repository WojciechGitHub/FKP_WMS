package pl.fkpsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.fkpsystem.model.Role;
import pl.fkpsystem.model.Volunteer;
import pl.fkpsystem.repository.RoleRepository;
import pl.fkpsystem.repository.VolunteerRepository;

@Service
public class VolunteerService {

    @Autowired
    private final VolunteerRepository volunteerRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    public VolunteerService(VolunteerRepository volunteerRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder){
        this.volunteerRepository=volunteerRepository;
        this.roleRepository=roleRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public Volunteer findByVolunteerName(String name){
        return volunteerRepository.findByName(name);
    }

    public void saveVolunteer(Volunteer volunteer){
        volunteer.setPassword(passwordEncoder.encode(volunteer.getPassword()));
        volunteer.setEnabled(1);
        Role volunteerRole=roleRepository.findByName("ROLE_REGISTERED");
        volunteer.getRoles().add(volunteerRole);
        volunteerRepository.save(volunteer);
    }

    /*
        public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("REGISTERED");
        if(userRole==null){
           userRole=new Role();
            userRole.setName("REGISTERED");
            roleRepository.save(userRole);

        }
        user.getRoles().add(userRole);
        //user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    */

}
