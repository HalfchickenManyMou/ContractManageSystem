package com.example.study.model.network.response;

import com.example.study.model.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DepartmentResponse {
    private Long idx;
    private String department;
    private List<Team> teamList = new ArrayList<>();
}
