package ru.fit.fitlyfe.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

import lombok.Data;

import static org.hibernate.cfg.AvailableSettings.USER;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long user_id;

    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @NotEmpty(message = "Name should not be empty")
    @Column(name = "user_name")
    private String username;

    @Size(min = 6, max = 32, message = "Password should be between 6 and 32 characters")
    @Column(name = "password_hash")
    private String password_hash;

    @Email
    @NotEmpty
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "height")
    private float height;

    @NotNull
    @Column(name = "weight")
    private float weight;

    @NotNull
    @Column(name = "date")
    private String date;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HealthData> healthDataList;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userProfile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhysicalActivityData> physicalActivityDataList;

}

