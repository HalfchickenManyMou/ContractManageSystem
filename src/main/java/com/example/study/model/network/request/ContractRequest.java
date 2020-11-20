package com.example.study.model.network.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractRequest {
    private Long            idx;

    private String          code;
    private String          name;
    private Integer         contractTypeIdx;
    private String          userCode;
    private Integer         departmentIdx;
    private String          content;

    private String          registerDate;
    private String          registerUser;
    private String          updateDate;
    private String          updateUser;

    private String          ownerName;
    private String          ownerBusinessNumber;
    private String          ownerAddress;

    private String          otherName;
    private String          otherBusinessNumber;
    private String          otherAddress;

    private String          startDate;
    private String          endDate;

    private String          contractAmount;
    private String          contractQty;

}