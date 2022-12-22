package com.example.trackyourbodychange.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.Relational;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "trainees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String username;

    @NotEmpty
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Pattern(regexp = "05[0-9]{8}")
    private String phone;

    @OneToMany(mappedBy = "trainee")
    @JsonIgnore
    private Set<Measurement> measurements;

    @OneToMany(mappedBy = "trainee")
    @JsonIgnore
    private Set<Schedule> schedules;
    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(updatable = true)
    private LocalDateTime updatedAt = LocalDateTime.now();
}
