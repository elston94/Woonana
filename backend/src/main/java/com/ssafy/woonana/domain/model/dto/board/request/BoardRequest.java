package com.ssafy.woonana.domain.model.dto.board.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ApiModel("BoardRequest")
public class BoardRequest {

    @ApiModelProperty(name = "작성할 글 제목", example="같이 1시간 산책 하실 분 구해요")
    private String title;
    @ApiModelProperty(name = "작성할 글 내용", example="동네 한바퀴만 돌겠습니다. ~~~ ")
    private String content;
    @ApiModelProperty(name = "만날 장소의 주소", example="대전광역시 유성구 동서대로 98-39 (SSAFY)")
    private String place;
    @ApiModelProperty(name = "운동 시작 시간(만날 시간)", example="2022-05-15T15:00:00")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime meetStartDate;
    @ApiModelProperty(name = "운동 종료 시간", example="2022-05-15T17:00:00")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime meetEndDate;
    @ApiModelProperty(name = "모집할 최대 인원", example="3")
    private int maxNumber;
    @ApiModelProperty(name = "모집 형태(신청제 - 0, 선착순 - 1)", example="0")
    private int participationOption;
    @ApiModelProperty(name = "만나서 할 운동 종류", example="2")
    private Long exerciseId;
    @ApiModelProperty(name = "사진 등록")
    private MultipartFile file;


    public BoardRequest(String title, String content, String place, LocalDateTime meetStartDate, LocalDateTime meetEndDate, int maxNumber, int participationOption, long exerciseId, MultipartFile file) {
        this.title = title;
        this.content = content;
        this.place = place;
        this.meetStartDate = meetStartDate;
        this.meetEndDate = meetEndDate;
        this.maxNumber = maxNumber;
        this.participationOption = participationOption;
        this.exerciseId = exerciseId;
        this.file = file;
    }
}
