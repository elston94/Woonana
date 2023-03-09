package com.ssafy.woonana.domain.model.dto.board.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel("BoardUpdateRequest")
public class BoardUpdateRequest {

    @ApiModelProperty(name = "글 제목", example="같이 1시간 산책 하실 분 구해요")
    private String title;
    @ApiModelProperty(name = "글 내용", example="동네 한바퀴만 돌겠습니다. ~~~ ")
    private String content;
    @ApiModelProperty(name = "모집할 최대 인원", example="3")
    private int maxNumber;
}
