package com.example.JobSeekingSystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "LENGTH(title) >= 5")

public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(30) not null")
    @NotEmpty(message = "Job Post Title cannot be empty")
    @Size(min = 5, message = "Job Post Title must be more than 4 character")
    private String title;

    @Column(columnDefinition = "varchar(50) not null")
    private String description;

    @Column(columnDefinition = "datetime default current_timestamp")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime postingDate;

    @PrePersist
    public void prePersist() {
        if (postingDate == null)
            postingDate = LocalDateTime.now();
    }
}
