package com.miex.user.config;

import com.miex.domain.dto.res.Response;
import com.miex.enums.RequestEnum;
import com.miex.exception.RequestException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

/**
 * @author liutz
 * @date 2022/1/26
 */
@ControllerAdvice
public class DefaultExceptionHandler {

	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	@ResponseBody
	public Response<String> exceptionHandler(HttpServletRequest req, HttpServletResponse res, MethodArgumentNotValidException e) {
		Response<String> response = initResponse(e);
		response.setCode(30001);
		response.setMsg("参数不合法:" + req.getRequestURI());
		return response;
	}

	@ExceptionHandler(value = ConstraintViolationException.class)
	@ResponseBody
	public Response<String> exceptionHandler(HttpServletRequest req, HttpServletResponse res, ConstraintViolationException e) {
		Response<String> response = initResponse(e);
		response.setCode(30001);
		response.setMsg("参数不合法:" + req.getRequestURI());
		return response;
	}

	@ExceptionHandler(value = RequestException.class)
	@ResponseBody
	public Response<String> exceptionHandler(HttpServletRequest req, HttpServletResponse res, RequestException e) {
		Response<String> response = initResponse(e);
		response.setCode(30001);
		response.setMsg("参数不合法:" + req.getRequestURI());
		return response;
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Response<String> exceptionHandler(HttpServletRequest req, HttpServletResponse res, Exception e) {
		Response<String> response = initResponse(e);
		response.setCode(RequestEnum.UNKNOWN_ERROR.getCode());
		response.setMsg("未处理的异常");
		return response;
	}

	private Response<String> initResponse(Exception e) {
		Response<String> response = new Response<>();
		response.setError(e.getMessage());
		StackTraceElement[] stackTrace = e.getStackTrace();
		if (stackTrace!=null) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < Math.min(stackTrace.length, 10); i++) {
				sb.append(stackTrace[i]).append("\r\n");
			}
			response.setData(sb.toString());
		}
		return response;
	}
}
