package com.ssafy.woonana.domain.model.entity.participation;

import com.ssafy.woonana.domain.model.entity.BaseTimeEntity;
import com.ssafy.woonana.domain.model.entity.board.Board;
import com.ssafy.woonana.domain.model.entity.user.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"participatedCheck", "allowed"})
@Table(name = "participations")
public class Participation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participation_id")
    private Long id;
    @Column(name = "participation_participated_check")
    private boolean participatedCheck;
    @Column(name = "participation_allowed")
    private boolean allowed;

    @CreatedDate
    @Column(name = "participation_created_date")
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(name = "participation_last_modified_date")
    private LocalDateTime lastModifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public Participation(User user, Board board, boolean allowed) {
        this.user = user;
        user.addParticipations(this);

        this.board = board;
        board.addParticipations(this);

        this.allowed = allowed;
    }
}
