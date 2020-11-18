package com.example.study.model.entity;

import com.example.study.model.enumclass.AuthType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;
    private long contract_idx;
    private int department_idx;
    @Column(name="team_idx")
    private int teamidx;
    @Enumerated(EnumType.STRING)
    private AuthType auth_type;
    private LocalDateTime registerDate;
    private String registerUser;
    private LocalDateTime updateDate;
    private String updateUser;

}
