package com.example.trackyourbodychange.Service;

import com.example.trackyourbodychange.Exception.ApiException;
import com.example.trackyourbodychange.Model.Schedule;
import com.example.trackyourbodychange.Repository.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public List<Schedule> all() {
        return scheduleRepository.findAll();
    }

    public void create(Schedule schedule) {
        Schedule schedule1 = scheduleRepository.save(schedule);
        if (schedule1 == null){
            throw new ApiException("Error Occur");
        }
    }

    public Boolean update(Integer id, Schedule schedule) {
        Schedule currentSchedule = scheduleRepository.findScheduleById(id);
        if (currentSchedule == null) {
            throw new ApiException("Error Occur");
        }
        currentSchedule.setName(schedule.getName());
        currentSchedule.setDate(schedule.getDate());
        currentSchedule.setUpdatedAt(LocalDateTime.now());
        scheduleRepository.save(currentSchedule);
        return true;
    }

    public Boolean delete(Integer id) {
        Schedule schedule = scheduleRepository.findScheduleById(id);
        if (schedule == null) {
            throw new ApiException("Error Occur");
        }
        scheduleRepository.delete(schedule);
        return true;
    }

    public Schedule getScheduleById(Integer id) {
        Schedule schedule = scheduleRepository.findScheduleById(id);
        if (schedule == null) {
            throw new ApiException("Wrong id");
        }
        return schedule;
    }
}
