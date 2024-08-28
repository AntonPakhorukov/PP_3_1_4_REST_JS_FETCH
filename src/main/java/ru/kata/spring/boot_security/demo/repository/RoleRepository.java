package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("select r from Role r where r.role=:rolename")
    Role findByName(@Param("rolename") String name);
}
