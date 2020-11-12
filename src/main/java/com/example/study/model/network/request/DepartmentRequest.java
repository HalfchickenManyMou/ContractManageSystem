package com.example.study.model.network.request;

import com.example.study.model.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DepartmentRequest {
    private Long idx;
    private String department;
    private List<Team> teamList;
}
