package com.example.trackyourbodychange.Repository;

import com.example.trackyourbodychange.Model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer> {
    Trainee findTraineeById(Integer id);
}

