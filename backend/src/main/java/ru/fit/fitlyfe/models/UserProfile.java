package ru.fit.fitlyfe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Collection;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "user_profile")
public class UserProfile implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @NotEmpty(message = "Name should not be empty")
    @Column(name = "user_name")
    private String username;

    @Column(name = "password_hash")
    private String passwordHash;

    @Email
    @NotEmpty
    @Column(name = "email")
    private String email;


    @Column(name = "height")
    private float height;


    @Column(name = "weight")
    private float weight;


    @Column(name = "date")
    private String date;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HealthData> healthDataList;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhysicalActivityData> physicalActivityDataList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return passwordHash;
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
}

