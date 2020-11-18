package com.example.study.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String code;
    private String name;
    private Long contractTypeIdx;
    private String userCode;
    private Long departmentIdx;
    private String content;
    private String ownerName;
    private String ownerBusinessNumber;
    private String ownerAddress;
    private String otherName;
    private String otherBusinessNumber;
    private String otherAddress;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    private String contractAmount;
    private String contractQty;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private String registerUser;
    private String updateUser;



}