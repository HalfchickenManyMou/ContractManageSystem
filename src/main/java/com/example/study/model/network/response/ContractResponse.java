package com.example.study.model.network.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ContractResponse {
    private Long            idx;

    private String          code;
    private String          name;
    private Integer         contractTypeIdx;
    private String          userCode;
    private Integer         departmentIdx;
    private String          content;

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

    private String          registerDate;
    private String          registerUser;
    private String          updateDate;
    private String          updateUser;
}
