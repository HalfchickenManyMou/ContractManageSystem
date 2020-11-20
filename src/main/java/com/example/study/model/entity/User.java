package com.example.study.model.entity;

<<<<<<< HEAD
=======
import com.example.study.model.enumclass.UserStatus;
>>>>>>> 1db4c611f3674d06ba7213925fd729f4fdf0275a
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
<<<<<<< HEAD
import org.springframework.data.annotation.CreatedDate;
=======
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
>>>>>>> 1db4c611f3674d06ba7213925fd729f4fdf0275a
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
<<<<<<< HEAD
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

    @ManyToOne
    @JoinColumn(name = "department_idx")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "team_idx")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "rank_idx")
    private Ranks rank;

    @CreatedDate
    private LocalDateTime registerDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    //TODO : 로그인 유저정보 저장
    private String registerUser;
    private String updateUser;

=======
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@ToString(exclude = {"orderGroupList"})
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserStatus status;  // REGISTERED / UNREGISTERED/ WAITING /

    private String email;

    private String phoneNumber;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    private String updatedBy;

//    // User 1 : N OrderGroup
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    private List<OrderGroup> orderGroupList;
>>>>>>> 1db4c611f3674d06ba7213925fd729f4fdf0275a

}
