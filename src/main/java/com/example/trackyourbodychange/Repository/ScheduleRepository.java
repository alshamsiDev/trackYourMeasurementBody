package com.example.trackyourbodychange.Repository;

import com.example.trackyourbodychange.Model.Schedule;
import com.example.trackyourbodychange.Model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    Schedule findScheduleById(Integer id);

    Set<Schedule> findSchedulesByTraineeOrderByCreatedAt(Trainee trainee);
}

