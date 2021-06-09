package com.miex.domain.dto.req;

import com.miex.domain.dto.AssessRequest;
import lombok.Data;

@Data
public class UserInfoRequest implements AssessRequest {
    String userName;
    String password;
}
