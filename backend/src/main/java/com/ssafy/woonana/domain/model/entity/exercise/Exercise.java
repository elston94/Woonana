package com.ssafy.woonana.domain.model.entity.exercise;

import com.ssafy.woonana.domain.model.entity.board.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Exercise {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private Long id;
    @Column(name = "exercise_name")
    private String name;
    @Column(name = "exercise_image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "exercise")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "exercise")
    private List<ExerciseLog> exerciseLogs = new ArrayList<>();

    public Exercise(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public void addExerciseLogs(ExerciseLog exerciseLog){
        exerciseLogs.add(exerciseLog);
        exerciseLog.setExercise(this);
    }
}
