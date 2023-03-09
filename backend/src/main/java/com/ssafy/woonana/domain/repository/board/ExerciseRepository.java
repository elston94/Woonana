package com.ssafy.woonana.domain.repository.board;

import com.ssafy.woonana.domain.model.entity.exercise.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    Exercise findExerciseByName(String exerciseName);
}
