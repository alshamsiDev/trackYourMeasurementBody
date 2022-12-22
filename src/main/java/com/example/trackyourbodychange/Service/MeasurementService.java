package com.example.trackyourbodychange.Service;

import com.example.trackyourbodychange.Exception.ApiException;
import com.example.trackyourbodychange.Model.Measurement;
import com.example.trackyourbodychange.Repository.MeasurementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    public List<Measurement> all() {
        return measurementRepository.findAll();
    }

    public void create(Measurement measure) {
        measurementRepository.save(measure);
    }

    public Boolean update(Integer id, Measurement trainee) {
        Measurement currentMeasurement = measurementRepository.findMeasurementById(id);
        if (currentMeasurement == null) {
            return false;
        }
        currentMeasurement.setHeight(trainee.getHeight());
        currentMeasurement.setWeight(trainee.getWeight());
        currentMeasurement.setMuscleWeight(trainee.getMuscleWeight());
        currentMeasurement.setFatPercentage(trainee.getFatPercentage());
        currentMeasurement.setUpdatedAt(LocalDateTime.now());
        measurementRepository.save(currentMeasurement);
        return true;
    }

    public Boolean delete(Integer id) {
        Measurement trainee = measurementRepository.findMeasurementById(id);
        if (trainee == null) {
            return false;
        }
        measurementRepository.delete(trainee);
        return true;
    }

    public Measurement getMeasureById(Integer id) {
        Measurement measurement = measurementRepository.findMeasurementById(id);
        if (measurement == null) {
            throw new ApiException("Wrong id");
        }
        return measurement;
    }
}
