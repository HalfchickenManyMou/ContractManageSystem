
package com.example.study.model.network.request;


import com.example.study.model.enumclass.AuthType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AuthUserRequest {
    private Long idx;
    private Long contract_idx;
    private Integer department_idx;
    private Integer team_idx;
    private String useridx;
    private AuthType auth_type;
}