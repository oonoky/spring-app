package kz.narxoz.springapp.repository;

import kz.narxoz.springapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}