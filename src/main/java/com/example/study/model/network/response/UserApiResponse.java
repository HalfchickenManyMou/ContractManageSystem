package com.example.study.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiResponse {

    private String userCode;

    private String name;

    private String email;

    private String pwd;


    private Integer departmentIdx;

    private Integer teamIdx;

    private Integer rankIdx;

    private String phoneNumber;
    private LocalDateTime registerDate;
    private String registerUser;
    private LocalDateTime updateDate;
    private String updateUser;



}
