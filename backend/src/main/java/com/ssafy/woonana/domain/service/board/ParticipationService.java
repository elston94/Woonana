package com.ssafy.woonana.domain.service.board;

import com.ssafy.woonana.domain.model.dto.board.response.PickListDetail;
import com.ssafy.woonana.domain.model.dto.board.response.PickListResponse;
import com.ssafy.woonana.domain.model.dto.user.response.UserParticipatedCheck;
import com.ssafy.woonana.domain.model.entity.board.Board;
import com.ssafy.woonana.domain.model.entity.participation.Participation;
import com.ssafy.woonana.domain.model.entity.user.User;
import com.ssafy.woonana.domain.repository.board.BoardRepository;
import com.ssafy.woonana.domain.repository.participation.ParticipationRepository;
import com.ssafy.woonana.domain.repository.user.UserRepository;
import com.ssafy.woonana.error.exception.custom.ParticipationDuplicateException;
import com.ssafy.woonana.error.exception.custom.ParticipationIsFullException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ParticipationService {

    private final ParticipationRepository participationRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long register(Long boardId, Long userId) {

        Board findBoard = boardRepository.findById(boardId).get();
        User requestUser = userRepository.findById(userId).get();

        // 모집 옵션이 승인 거절 방법(0)일 때 승인 여부(isUserAllowed)는 false
        boolean isUserAllowed = false;

        if (findBoard.getParticipationOption() == 1) { // 모집 옵션이 선착순(1)일 때
            // 이 시간대에 참여 신청된 board가 있으면 종료
            this.isTimeOverlapped(requestUser.getUserId(), findBoard.getMeetStartDate(), findBoard.getMeetEndDate());
            isUserAllowed = true;
            findBoard.updateAllowedMemberCount();
        }

        Participation participation = new Participation(requestUser, findBoard, isUserAllowed);
        participationRepository.save(participation);
        return participation.getId();
    }

    public PickListResponse getAppliedList(Long boardId) {

        // boardId에 연관된 participateId 모든 목록 출력
        List<Participation> findAppliedList = participationRepository.findParticipationByBoardId(boardId);
        PickListResponse response = new PickListResponse();
        for (Participation p : findAppliedList) {
            PickListDetail detail = new PickListDetail();
            User findUser = userRepository.findById(p.getUser().getUserId()).get();
            detail.setParticipationId(p.getId());
            detail.setUserId(findUser.getUserId());
            detail.setUserNickname(findUser.getUserNickname());
            detail.setProfileUrl(findUser.getUserProfileUrl());
            detail.setParticipationAllowed(p.isAllowed());
            detail.setUserRatingScore(findUser.getUserRatingScore());
            response.addPickListDetail(detail);
        }

        return response;
    }

    @Transactional
    public void approve(Long participationId) {
        Participation findParticipation = participationRepository.findById(participationId).get();
        Board findBoard = boardRepository.findById(findParticipation.getBoard().getId()).get();

        // 해당 board의 인원이 다 찼으면 종료
        this.isStatusClose(findBoard.getId());
        // 이 시간대에 참여 신청된 board가 있으면 종료
        this.isTimeOverlapped(findParticipation.getUser().getUserId(), findBoard.getMeetStartDate(), findBoard.getMeetEndDate());

        findParticipation.setAllowed(true);
        findBoard.updateAllowedMemberCount();

        participationRepository.save(findParticipation);
    }

    @Transactional
    public void refuse(Long participationId) {

        // TODO : 신청 거절시 변경할 데이터가 없어서 일단 삭제로 함
        Participation findParticipation = participationRepository.findById(participationId).get();
        participationRepository.delete(findParticipation);
    }
    @Transactional
    public void cancel(Long participationId) {

        Participation findParticipation = participationRepository.findById(participationId).get();
        Board findBoard = boardRepository.findById(findParticipation.getBoard().getId()).get();
        findBoard.afterUserCancel();

        participationRepository.delete(findParticipation);
    }

    public void isUserRegistered(Long boardId, Long userId) {
        Participation findParticipation = participationRepository.findParticipation(boardId, userId);
        if (findParticipation != null)
            throw new ParticipationDuplicateException("이미 참여 신청된 사용자입니다.");
    }

    private void isTimeOverlapped(Long userId, LocalDateTime meetStartDate, LocalDateTime meetEndDate) {
        List<UserParticipatedCheck> participationsByTime = participationRepository.findParticipationsByTime(userId, meetStartDate, meetEndDate);
        if (participationsByTime.size() != 0)
            throw new ParticipationDuplicateException("이 시간대에 이미 참여 신청된 운동이 있습니다.");
    }

    public void isStatusClose(Long boardId) {
        Board findBoard = boardRepository.findById(boardId).get();
        if (findBoard.getStatus().equals("CLOSE"))
            throw new ParticipationIsFullException();
    }

    // userId가 참여한 게시글 목록
    public List<Map<String, Long>> participateBoardList(Long userId){
        List<Long> boardIdList=participationRepository.participateBoardList(userId);
        List<Map<String, Long>> result = new ArrayList<>();

        for(Long boardId: boardIdList){
            Map<String, Long> board=new HashMap<>();
            board.put("boardId", boardId);
            result.add(board);
        }

        return result;
    }
}
