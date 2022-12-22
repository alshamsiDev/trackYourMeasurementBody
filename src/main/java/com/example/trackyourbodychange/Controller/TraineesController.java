package com.example.trackyourbodychange.Controller;

import com.example.trackyourbodychange.Model.Measurement;
import com.example.trackyourbodychange.Model.Schedule;
import com.example.trackyourbodychange.Model.Trainee;
import com.example.trackyourbodychange.Service.TraineeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trainees")
public class TraineesController {
    private final TraineeService traineeService;

    @GetMapping("/all")
    public ResponseEntity<List<Trainee>> getTrainees() {
        return ResponseEntity.status(200).body(traineeService.all());
    }

    @PostMapping("/create")
    public ResponseEntity<String> addTrainee(@RequestBody @Valid Trainee trainee, Errors errors) {
        System.out.println(trainee);
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        traineeService.create(trainee);
        return ResponseEntity.status(200).body("trainee created");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTrainee(@PathVariable Integer id, @RequestBody @Valid Trainee trainee, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        traineeService.update(id, trainee);
        return ResponseEntity.status(200).body("Trainee Updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTrainee(@PathVariable Integer id) {
        traineeService.delete(id);
        return ResponseEntity.status(200).body("Trainee Deleted");
    }


    @GetMapping("{id}/measurements")
    public Set<Measurement> Measurements(@PathVariable Integer id) {
        return traineeService.measurements(id);
    }

    @GetMapping("{id}/schedules")
    public Set<Schedule> schedules(@PathVariable Integer id) {
        return traineeService.schedules(id);
    }

    @GetMapping("{id}/get")
    public ResponseEntity getTrainee(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(traineeService.getTrainee(id));
    }


    @GetMapping("{id}/last")
    public ResponseEntity  getLas(@PathVariable Integer id){
        Measurement measurement = traineeService.getLast(id);
        return ResponseEntity.status(200).body(measurement);
    }

}
