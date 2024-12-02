package com.example.JobSeekingSystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints =
        "LENGTH(name) >= 5 AND " +
                "LENGTH(password) >= 22 AND " +
                "(role = 'JOB_SEEKER' OR role = 'EMPLOYER')"
)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(16) not null unique")
    @NotEmpty(message = "User Name cannot be empty")
    @Size(min = 5, message = "User Name must be more than 4 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "User Name must contain letters only")
    private String name;

    @Column(columnDefinition = "varchar(30) not null unique")
    @Email(message = "User Email must be in valid email format")
    private String email;

    @Column(columnDefinition = "varchar(40) not null")
    @NotEmpty(message = "User Password cannot be empty")
    @Pattern(regexp = "^[0-9]+$", message = "User Password must contain numbers only")
    @Size(min = 22, message = "User Password must be more than 21 characters")
    private String password;

    @Column(columnDefinition = "varchar(10) not null")
    @NotEmpty(message = "User Role cannot be empty")
    @Pattern(regexp = "^(JOB_SEEKER|EMPLOYER)$", message = "User Role must be either JOB_SEEKER or EMPLOYER")
    private String role;
}
