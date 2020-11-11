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
public class AuthDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idx;
    private long contract_idx;
    @Column(name="department_idx")
    private int departmentidx;
    @Enumerated(EnumType.STRING)
    private AuthType auth_type;
    private LocalDateTime registerDate;
    private String registerUser;
    private LocalDateTime updateDate;
    private String updateUser;
}
