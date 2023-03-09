package com.ssafy.woonana.domain.model.dto.board.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PickListResponse {

    private List<PickListDetail> allAppliedUsers = new ArrayList<>();

    public void addPickListDetail(PickListDetail detail) {
        allAppliedUsers.add(detail);
    }
}
