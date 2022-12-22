package com.example.trackyourbodychange.Repository;

import com.example.trackyourbodychange.Model.Measurement;
import com.example.trackyourbodychange.Model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    Measurement findMeasurementById(Integer id);

    Set<Measurement> findMeasurementsByTraineeOrderByCreatedAt(Trainee trainee);

    Measurement findFirstByTraineeOrderByCreatedAtDesc(Trainee trainee);
}

