package com.miex.rest;

import com.miex.domain.dto.UnifiedResponse;
import com.miex.domain.dto.req.UserInfoRequest;
import com.miex.domain.dto.res.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("user")
public class UserRest {
    @GetMapping("login")
    public UnifiedResponse<LoginResponse> login(@RequestBody UserInfoRequest userInfo){
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken("asdasd6a7d74sf3f5jf5h");
        return new UnifiedResponse<>();
    }
}
