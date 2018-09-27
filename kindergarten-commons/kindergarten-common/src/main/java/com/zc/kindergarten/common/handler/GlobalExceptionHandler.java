package com.zc.kindergarten.common.handler;

import com.zc.kindergarten.common.error.SystemErrors;
import com.zc.kindergarten.common.exception.BaseException;
import com.zc.kindergarten.common.exception.ParamsException;
import com.zc.kindergarten.common.exception.RequestTimeOutException;
import com.zc.kindergarten.common.exception.SysException;
import com.zc.kindergarten.common.msg.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	/**
	 * Http请求时，参数异常
	 *
	 * @param
	 * @param e
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseBody
	public ResponseEntity handlerRequstParams(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
		log.error("\n发生的异常：方法参数类型绑定异常", e);
		return new ResponseEntity(SystemErrors.METHOD_ARGUMENT_TYPE_MISMATCH_EXCEPTION);
	}

	/**
	 * Http请求时，参数异常
	 *
	 * @param
	 * @param e
	 */
	@ExceptionHandler(ServletException.class)
	@ResponseBody
	public ResponseEntity ServletException(ServletException e, HttpServletRequest request) {
		log.error("\n发生的异常：请求处理异常", e);
		return new ResponseEntity(SystemErrors.SERVLET_EXCEPTION);
	}

	/**
	 * 服务器内部出错
	 *
	 * @param
	 * @param e
	 */
	@ExceptionHandler(SysException.class)
	@ResponseBody
	public ResponseEntity SysException(SysException e, HttpServletRequest request) {
		log.error("\n发生的异常：自定义系统异常", e);
		return new ResponseEntity(e.getError().getCode(), e.getError().getMessage());
	}

	/**
	 * 请求超时
	 *
	 * @param
	 * @param e
	 */
	@ExceptionHandler(RequestTimeOutException.class)
	@ResponseBody
	public ResponseEntity RequestTimeOutException(RequestTimeOutException e, HttpServletRequest request) {
		log.error("\n发生的异常：请求超时，异常原因：" + e.getError().getMessage() + (e.getData() == null ? "" : (",data=" + e.getData())), e);
		return new ResponseEntity(e.getError().getCode(), e.getError().getMessage());
	}

	/**
	 * 参数出错
	 *
	 * @param
	 * @param e
	 */
	@ExceptionHandler(ParamsException.class)
	@ResponseBody
	public ResponseEntity ParamsException(ParamsException e, HttpServletRequest request) {
		log.error("\n发生的异常：参数错误", e);
		return new ResponseEntity(e.getError().getCode(), e.getError().getMessage());
	}


	/**
	 * 基础异常处理
	 *
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BaseException.class)
	@ResponseBody
	public ResponseEntity BaseException(HttpServletRequest request, BaseException e) {
		log.error("\n发生的异常：BaseException", e);
		return new ResponseEntity(e.getError().getCode(), e.getError().getMessage());
	}

	/**
	 * 统一未知异常处理
	 *
	 * @param request
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity handleGlobal(HttpServletRequest request, Exception e) {
		log.error("\n发生的异常：Exception", e);
		return new ResponseEntity(SystemErrors.SYSTEM_ERROR);
	}

	/**
	 * 请求参数格式异常处理
	 *
	 * @param exception
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity MethodArgumentNotValidHandler(MethodArgumentNotValidException exception, HttpServletRequest request) throws Exception {
		BindingResult result = exception.getBindingResult();
		log.error("\n发生的异常：请求参数格式异常", exception);
		return bindParamValid(result);
	}

	/**
	 * 请求参数格式异常处理
	 *
	 * @param exception
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(value = BindException.class)
	@ResponseBody
	public ResponseEntity BindException(BindException exception, HttpServletRequest request) throws Exception {
		BindingResult result = exception.getBindingResult();
		log.error("\n发生的异常：请求参数绑定异常", exception);
		return bindParamValid(result);
	}

	/**
	 * 数据绑定参数校验
	 *
	 * @param result
	 * @return
	 */
	private ResponseEntity bindParamValid(BindingResult result) {
		List<Object> invalidArguments = new ArrayList<>();
		if (result.hasErrors()) {
			for (FieldError error : result.getFieldErrors()) {
				Map<String, String> map = new HashMap<>();
				map.put("filed", error.getField());
				map.put("errorMsg", error.getDefaultMessage());
				invalidArguments.add(map);
			}
		}
		return new ResponseEntity(SystemErrors.REQUEST_PARAM_ERROR);
	}
}
