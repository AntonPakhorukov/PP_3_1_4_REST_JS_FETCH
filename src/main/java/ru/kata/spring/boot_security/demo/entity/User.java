package ru.kata.spring.boot_security.demo.entity;



import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
public class User implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getRole()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotEmpty(message = "Name should not be empty")
//    @Size(min = 2, max = 20, message = "Name should be between 2 and 30 characters")
    @Setter
    @Getter
    @Column(name = "username")
    private String name;


//    @NotEmpty
    @Getter
    @Setter
    private String password;

    @Setter
    @Getter
//    @Min(value = 0, message = "Age should be greater than 0")
    private String age;

    @Setter
    @Getter
//    @Email(message = "Email should be valid")
//    @NotEmpty(message = "Email should not be empty")
    private String email;

    @Override
    public String toString() {
        return "User: id = " + id + ", name = '" + name + "', age = '"
                + age + "', email = '" + email + "', roles = '" + getRoles() + "'";
    }

    @Setter
    @Getter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles = new ArrayList<>();

    public User() {
    }

    public User(String name, String password, String age, String email) {
        this.name = name;
        this.password = password;
        this.age = age;
        this.email = email;
    }
}