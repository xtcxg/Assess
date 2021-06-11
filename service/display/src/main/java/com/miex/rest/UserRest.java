package com.miex.rest;

import com.miex.domain.User;
import com.miex.domain.dao.impl.UserDAO;
import com.miex.domain.dto.UnifiedResponse;
import com.miex.domain.dto.req.UserInfoRequest;
import com.miex.domain.dto.res.LoginResponse;
import com.miex.exception.ESException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequestMapping("user")
public class UserRest {

    @Autowired
    UserDAO userDAO;

    @GetMapping("login")
    public UnifiedResponse<LoginResponse> login(@RequestBody UserInfoRequest userInfo){
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken("asdasd6a7d74sf3f5jf5h");
        return new UnifiedResponse<>();
    }

    @PostMapping("add")
    public UnifiedResponse add(@RequestBody User user) throws ESException {
        userDAO.insert(user);
        return new UnifiedResponse();
    }

    /**
     * @NotNull(message = "id不能为null") @NotBlank(message = "id不能为null") @NotEmpty(message = "id不能为空")
     * @param id
     * @return
     * @throws ESException
     */
    @GetMapping("select-by-id")
    public UnifiedResponse<User> selectById(@RequestParam("id") @NotBlank(message = "id不能为空") String id) throws ESException {
        User user = userDAO.selectById(id);
        if(null == user) {
            throw new ESException("查询结果为空,id:" + id);
        }
        return new UnifiedResponse<>(user);
    }
}
