package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class User {

    @Id
//    @GeneratedValue(generator="uuid")     1
//    @GenericGenerator(name="uuid", strategy = "uuid2")    1
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID" , strategy = "org.hibernate.id.UUIDGenerator")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable=false, nullable=false)
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
