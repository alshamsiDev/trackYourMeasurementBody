package com.example.trackyourbodychange.Controller;

import com.example.trackyourbodychange.Model.Measurement;
import com.example.trackyourbodychange.Service.MeasurementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/measurements")
public class MeasurementsController {
    private final MeasurementService measurementService;

    @GetMapping("/all")
    public ResponseEntity<List<Measurement>> getTrainees() {
        return ResponseEntity.status(200).body(measurementService.all());
    }

    @PostMapping("/create")
    public ResponseEntity<String> addTrainee(@RequestBody @Valid Measurement measurement, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        measurementService.create(measurement);
        return ResponseEntity.status(200).body("measurement created");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMeasurement(@PathVariable Integer id, @RequestBody @Valid Measurement measurement, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        measurementService.update(id, measurement);
        return ResponseEntity.status(200).body("Measurement Updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMeasurement(@PathVariable Integer id) {
        measurementService.delete(id);
        return ResponseEntity.status(200).body("Measurement Deleted");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getMeasurement(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(measurementService.getMeasureById(id));
    }

}
