package com.ssafy.woonana.domain.model.dto.exercise.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@ApiModel("ExerciseRequest")
public class ExerciseRequest {

    @ApiModelProperty(name = "등록할 운동의 이름 배열", example="['탁구', '배드민턴', '산책']")
    private String[] exerciseNames;
    @ApiModelProperty(name = "등록한 이름과 일치하는 위치에 해당하는 운동의 마커 이미지 배열",
            example="['notion://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F1d52aa6f-4c90-4691-81f5-42adc211a129%2FUntitled.png?table=block&id=9a867d86-aaab-431d-8ef0-cac120e96dec&spaceId=2e5143a8-214e-4694-8b9c-8f3d5a794b23&width=2000&userId=ec79b72b-a965-4117-9ab7-09e716a963d9&cache=v2', 'notion://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F1d52aa6f-4c90-4691-81f5-42adc211a129%2FUntitled.png?table=block&id=9a867d86-aaab-431d-8ef0-cac120e96dec&spaceId=2e5143a8-214e-4694-8b9c-8f3d5a794b23&width=2000&userId=ec79b72b-a965-4117-9ab7-09e716a963d9&cache=v2', 'notion://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F1d52aa6f-4c90-4691-81f5-42adc211a129%2FUntitled.png?table=block&id=9a867d86-aaab-431d-8ef0-cac120e96dec&spaceId=2e5143a8-214e-4694-8b9c-8f3d5a794b23&width=2000&userId=ec79b72b-a965-4117-9ab7-09e716a963d9&cache=v2']")
    private String[] exerciseImageUrls;

    public ExerciseRequest(String[] exerciseNames, String[] exerciseImageUrls) {

        this.exerciseNames = exerciseNames;
        this.exerciseImageUrls = exerciseImageUrls;
    }
}
