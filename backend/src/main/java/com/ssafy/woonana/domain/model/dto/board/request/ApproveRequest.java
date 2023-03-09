package com.ssafy.woonana.domain.model.dto.board.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ApproveRequest {
    @ApiModelProperty(name = "작성할 글 제목", example="같이 1시간 산책 하실 분 구해요")
    private Long participationId;
    private Long userId;
    private Long boardId;
    private Boolean allowed;
}
