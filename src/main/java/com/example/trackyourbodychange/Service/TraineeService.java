package com.example.trackyourbodychange.Service;

import com.example.trackyourbodychange.Exception.ApiException;
import com.example.trackyourbodychange.Model.Measurement;
import com.example.trackyourbodychange.Model.Schedule;
import com.example.trackyourbodychange.Model.Trainee;
import com.example.trackyourbodychange.Repository.MeasurementRepository;
import com.example.trackyourbodychange.Repository.ScheduleRepository;
import com.example.trackyourbodychange.Repository.TraineeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class TraineeService {
    private final TraineeRepository traineeRepository;
    private final MeasurementRepository measurementRepository;
    private final ScheduleRepository scheduleRepository;

    public List<Trainee> all() {
        return traineeRepository.findAll();
    }

    public void create(Trainee trainee) {
        Trainee t = traineeRepository.save(trainee);
        if (t == null) {
            throw new ApiException("Error Occur");
        }
    }

    public Boolean update(Integer id, Trainee trainee) {
        Trainee currentTrainee = traineeRepository.findTraineeById(id);
        if (currentTrainee == null) {
            throw new ApiException("Error Occur");
        }
        currentTrainee.setName(trainee.getName());
        currentTrainee.setEmail(trainee.getEmail());
        currentTrainee.setUsername(trainee.getUsername());
        currentTrainee.setPhone(trainee.getPhone());
        currentTrainee.setDateOfBirth(trainee.getDateOfBirth());
        currentTrainee.setUpdatedAt(LocalDateTime.now());
        traineeRepository.save(currentTrainee);
        return true;
    }

    public Boolean delete(Integer id) {
        Trainee trainee = traineeRepository.findTraineeById(id);
        if (trainee == null) {
            throw new ApiException("Error Occur");
        }
        traineeRepository.delete(trainee);
        return true;
    }

    public Set<Measurement> measurements(Integer id) {
        Trainee trainee = traineeRepository.findTraineeById(id);
        Set<Measurement> measurements = measurementRepository.findMeasurementsByTraineeOrderByCreatedAt(trainee);
        return measurements;
    }

    public Set<Schedule> schedules(Integer id) {
        Trainee trainee = traineeRepository.findTraineeById(id);
        Set<Schedule> schedules = scheduleRepository.findSchedulesByTraineeOrderByCreatedAt(trainee);
        return schedules;
    }

    public Trainee getTrainee(Integer id) {
        Trainee trainee = traineeRepository.findTraineeById(id);
        if (trainee == null) {
            throw new ApiException("Wrong id");
        }
        return trainee;
    }

    public Measurement getLast(Integer id) {
        Trainee trainee = traineeRepository.findTraineeById(id);
        Measurement measurement = measurementRepository.findFirstByTraineeOrderByCreatedAtDesc(trainee);
        return measurement;
    }
}
