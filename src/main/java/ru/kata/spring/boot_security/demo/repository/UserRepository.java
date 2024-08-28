package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    //    User findByUsername(String username);
    @Query("select u from User u left join fetch u.roles where u.name=:username")
    User findByName(@Param("username") String name);

    @Modifying
    @Transactional
    @Query(value = "insert into user_role(user_id, role_Id) values (userId, roleId)", nativeQuery = true)
    void insertUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

}
