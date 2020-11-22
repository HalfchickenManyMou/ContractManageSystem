package com.example.study.model.network.request;

import com.example.study.model.entity.Department;
import com.example.study.model.entity.Ranks;
import com.example.study.model.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private String userCode;
    private String name;
    private String email;
    private String phoneNumber;
    private Department department;
    private Team team;
    private Ranks rank;
    private String role;

    private Integer departmentIdx;
    private Integer teamIdx;
    private Integer rankIdx;


}
