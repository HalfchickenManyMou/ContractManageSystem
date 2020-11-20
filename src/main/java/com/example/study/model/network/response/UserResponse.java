package com.example.study.model.network.response;

import com.example.study.model.entity.Department;
import com.example.study.model.entity.Ranks;
import com.example.study.model.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private String userCode;
    private String name;
    private String email;
    private Department department;
    private Team team;
    private Ranks rank;
    private String phoneNumber;
    private String registerDate;
    private String registerUser;
    private String updateDate;
    private String updateUser;
}
