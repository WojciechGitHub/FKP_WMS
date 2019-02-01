package pl.fkpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.fkpsystem.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
