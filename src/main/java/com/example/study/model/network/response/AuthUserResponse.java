package com.example.study.model.network.response;

import com.example.study.model.enumclass.AuthType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
=======
import java.time.LocalDateTime;

>>>>>>> e96389c62b0c40b596d565947d1e477cb833bf2e
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserResponse {
<<<<<<< HEAD
=======
    private String authuser;
>>>>>>> e96389c62b0c40b596d565947d1e477cb833bf2e
    private Long idx;
    private Long contract_idx;
    private Integer department_idx;
    private Integer team_idx;
    private String useridx;
    private AuthType auth_type;
<<<<<<< HEAD
=======
    private LocalDateTime registerDate;
    private String registerUser;
    private LocalDateTime updateDate;
    private String updateUser;
>>>>>>> e96389c62b0c40b596d565947d1e477cb833bf2e
}
