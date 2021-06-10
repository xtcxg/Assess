package com.miex.exception;

import com.alibaba.fastjson.JSONObject;
import com.miex.domain.dto.UnifiedResponse;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.miex.enums.ResponseEnum.ES_OPERATION_FAIL;
import static com.miex.enums.ResponseEnum.PARAMS_VALID_FAIL;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public UnifiedResponse<String> ValidExceptionHandler(HttpServletRequest req, HttpServletResponse res, MethodArgumentNotValidException e){
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return new UnifiedResponse<>(PARAMS_VALID_FAIL,"参数校验不通过：" + fieldError.getDefaultMessage());
            }
        }
        return new UnifiedResponse<>(PARAMS_VALID_FAIL);
    }


    @ExceptionHandler(value = ESException.class)
    @ResponseBody
    public UnifiedResponse<String> ESExceptionHandler(HttpServletRequest req, HttpServletResponse res, ESException e){
//        e.getMessage();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
//        if (exceptions.hasErrors()) {
//            List<ObjectError> errors = exceptions.getAllErrors();
//            if (!errors.isEmpty()) {
//                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
//                FieldError fieldError = (FieldError) errors.get(0);
//                return new UnifiedResponse<>(PARAMS_VALID_FAIL,"参数校验不通过：" + fieldError.getDefaultMessage());
//            }
//        }
        return new UnifiedResponse<>(ES_OPERATION_FAIL,e.getMessage());
    }
}
