package com.example.study.model.entity;


import com.example.study.model.enumclass.AuthType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private Long contract_idx;
    private Integer department_idx;
    private Integer team_idx;
    @Column(name="user_code")
    private String useridx;
    @Enumerated(EnumType.STRING)
    private AuthType auth_type;
    private LocalDateTime registerDate;
    private String registerUser;
    private LocalDateTime updateDate;
    private String updateUser;

    private String authuser;
}



