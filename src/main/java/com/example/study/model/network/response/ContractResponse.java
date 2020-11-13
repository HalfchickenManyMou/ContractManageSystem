package com.example.study.model.network.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractResponse {

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
    private Date startDate;
    private Date endDate;
    private String contractAmount;
    private String contractQty;
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;
    private String registerUser;
    private String updateUser;
}
