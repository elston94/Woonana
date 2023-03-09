package com.ssafy.woonana.domain.model.entity.user;

import com.ssafy.woonana.domain.model.entity.board.Board;
import com.ssafy.woonana.domain.model.entity.evaluation.Evaluation;
import com.ssafy.woonana.domain.model.entity.exercise.ExerciseLog;
import com.ssafy.woonana.domain.model.entity.participation.Participation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name="kakao_id")
    private Long kakaoId;

    @Column
    private String userEmail;

    @Column
    private String userNickname;

    @Column
    private String userBirthday;

    @Column
    private String userSex;

    @Column
    private int userRatingScore;

    @Column
    private String userProfileUrl;

    @Column(name="access_token")
    private String accessToken; // 카카오 로그인에 필요한 액세스 토큰

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<Board> userBoards = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "board")
    private List<Participation> participations = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "exercise")
    private List<ExerciseLog> exerciseLogs = new ArrayList<>();

    public User(Long userId, String userEmail, String userBirthday, String userNickname, String userSex, String userProfileUrl) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userBirthday = userBirthday;
        this.userNickname = userNickname;
        this.userSex = userSex;
        this.userProfileUrl = userProfileUrl;
    }

    public void addParticipations(Participation participation) {
        participations.add(participation);
        participation.setUser(this);
    }

    public void addExerciseLogs(ExerciseLog exerciseLog){
        exerciseLogs.add(exerciseLog);
        exerciseLog.setUser(this);
    }

}
