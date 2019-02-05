package pl.fkpsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.fkpsystem.model.Role;
import pl.fkpsystem.model.Volunteer;

import java.util.HashSet;
import java.util.Set;

public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    private VolunteerService volunteerService;

    @Autowired
    public void setVolunteerRepository(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Volunteer volunteer = volunteerService.findByVolunteerName(username);
        if (volunteer == null) {
            throw new UsernameNotFoundException(username);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : volunteer.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(
                volunteer.getName(), volunteer.getPassword(), grantedAuthorities);
    }
}


