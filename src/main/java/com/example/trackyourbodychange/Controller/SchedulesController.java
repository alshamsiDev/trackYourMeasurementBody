package com.example.trackyourbodychange.Controller;

import com.example.trackyourbodychange.Model.Schedule;
import com.example.trackyourbodychange.Service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schedules")
public class SchedulesController {
    private final ScheduleService scheduleService;

    @GetMapping("/all")
    public ResponseEntity<List<Schedule>> getSchedules() {
        return ResponseEntity.status(200).body(scheduleService.all());
    }

    @PostMapping("/create")
    public ResponseEntity<String> addSchedule(@RequestBody @Valid Schedule schedule, Errors errors) {
        System.out.println(schedule);
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        scheduleService.create(schedule);
        return ResponseEntity.status(200).body("schedule created");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateSchedule(@PathVariable Integer id, @RequestBody @Valid Schedule schedule, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        scheduleService.update(id, schedule);
        return ResponseEntity.status(200).body("Schedule Updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteSchedule(@PathVariable Integer id) {
        scheduleService.delete(id);
        return ResponseEntity.status(200).body("Schedule Deleted");
    }


    @GetMapping("/get/{id}")
    public ResponseEntity getSchedule(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(scheduleService.getScheduleById(id));
    }


}
