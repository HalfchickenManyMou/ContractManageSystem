package com.example.study.model.network.response;

import com.example.study.model.enumclass.AuthType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserResponse {
    private Long idx;
    private Long contract_idx;
    private Integer department_idx;
    private Integer team_idx;
    private String useridx;
    private AuthType auth_type;
}
