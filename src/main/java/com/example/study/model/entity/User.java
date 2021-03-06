package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder @Accessors(chain = true)
@NamedEntityGraph(name = "UserWithOtherData", attributeNodes = { @NamedAttributeNode("department"), @NamedAttributeNode("team"), @NamedAttributeNode("rank")})
public class User {

    @Id
    private String userCode;

    private String name;
    private String email;

    private String pwd;

    private String phoneNumber;

    private Integer department_idx;

    private Integer team_idx;

    private Integer rank_idx;

    private String role;

    @ManyToOne
    @JoinColumn(name = "department_idx" , insertable = false, updatable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "team_idx" , insertable = false, updatable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "rank_idx" , insertable = false, updatable = false)
    private Ranks rank;

    @CreatedDate
    private LocalDateTime registerDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    //TODO : 로그인 유저정보 저장
    private String registerUser;
    private String updateUser;


}
