package com.ssafy.woonana.domain.model.entity.board;

import com.ssafy.woonana.domain.model.entity.BaseTimeEntity;
import com.ssafy.woonana.domain.model.entity.evaluation.Evaluation;
import com.ssafy.woonana.domain.model.entity.exercise.Exercise;
import com.ssafy.woonana.domain.model.entity.exercise.ExerciseLog;
import com.ssafy.woonana.domain.model.entity.participation.Participation;
import com.ssafy.woonana.domain.model.entity.user.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"title", "content", "maxNumber"})
@Table(name = "boards")
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;
    @Column(name = "board_title")
    private String title;
    @Column(name = "board_content")
    private String content;
    @CreatedDate
    @Column(name = "board_created_date")
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(name = "board_last_modified_date")
    private LocalDateTime lastModifiedDate;
    @Column(name = "board_place")
    private String place;
    @Column(name = "board_meet_start_date")
    private LocalDateTime meetStartDate;
    @Column(name = "board_meet_end_date")
    private LocalDateTime meetEndDate;
    @Column(name = "board_max_number")
    private int maxNumber;
    @Column(name = "board_allowed_number")
    private int allowedNumber;
    @Column(name = "board_status")
    @Convert(converter = StatusAttributeConverter.class)
    private String status; // OPEN, CLOSE, DONE
    @Column(name = "board_participation_option")
    private int participationOption;    // 승인/거절 방식 : 0, 선착순 방식 : 1
    @Column(name = "board_image_url")
    private String imageUrl;    // TODO: default 사진 url 등록하기

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board")
    private List<Participation> participations = new ArrayList<>();

    @OneToMany(mappedBy="exercise")
    private List<ExerciseLog> exerciseLogs = new ArrayList<>();

    @OneToMany(mappedBy="board")
    private List<Evaluation> evaluations = new ArrayList<>();

    public Board(String title, String content, String place, LocalDateTime meetStartDate, LocalDateTime meetEndDate, int maxNumber, int participationOption, Exercise exercise, User user) {
        this.title = title;
        this.content = content;
        this.place = place;
        this.meetStartDate = meetStartDate;
        this.meetEndDate = meetEndDate;
        this.maxNumber = maxNumber;
        this.status = "OPEN";
        this.participationOption = participationOption;
        this.user = user;

        user.getUserBoards().add(this);
        if (exercise != null) {
            changeExercise(exercise);
        }
    }

    public void setImageUrl(String imageurl) {
        this.imageUrl = imageurl;
    }

    public void changeExercise(Exercise exercise) {
        this.exercise = exercise;
        exercise.getBoards().add(this);
    }

    public void changeStatus(String status) {
        this.status = status;
    }

    public void addParticipations(Participation participation) {
        participations.add(participation);
        participation.setBoard(this);
    }

    public void updateAllowedMemberCount() {
        this.allowedNumber += 1;
        if (this.allowedNumber == this.maxNumber)
            this.changeStatus("CLOSE");
    }

    public void afterUserCancel() {
        this.allowedNumber -= 1;
        if (this.getStatus().equals("CLOSE"))
            this.changeStatus("OPEN");
    }

    public void addExerciseLogs(ExerciseLog exerciseLog){
        exerciseLogs.add(exerciseLog);
        exerciseLog.setBoard(this);
    }

    public void setBoardStatus(String status){
        this.status=status;
    }
}
