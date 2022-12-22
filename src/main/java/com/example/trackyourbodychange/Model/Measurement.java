package com.example.trackyourbodychange.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Weight cannot be empty")
    @Column(name = "weight")
    private Double weight;

    @NotNull(message = "Height cannot be empty")
    @Column(name = "height")
    private Double height;

    @NotNull(message = "Weight cannot be empty")
    @Column(name = "muscle_weight")
    private Double muscleWeight;
    @NotNull(message = "Height cannot be empty")
    @Column(name = "fat_percentage")
    private Double fatPercentage;

    @ManyToOne
    private Trainee trainee;

    @Column(updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(updatable = true)
    private LocalDateTime updatedAt = LocalDateTime.now();

}
