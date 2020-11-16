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

    private String ownerName;

    private String otherName;

    private String contractQty;

}
