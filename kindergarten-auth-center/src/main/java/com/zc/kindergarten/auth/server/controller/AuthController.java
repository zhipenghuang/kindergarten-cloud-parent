package com.zc.kindergarten.auth.server.controller;

import com.zc.kindergarten.common.error.AuthErrors;
import com.zc.kindergarten.common.error.SystemErrors;
import com.zc.kindergarten.common.exception.SysException;
import com.zc.kindergarten.common.msg.ListRestResponse;
import com.zc.kindergarten.common.msg.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@RestController
@RequestMapping("jwt")
@Slf4j
public class AuthController {

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> ok(@RequestParam("name") String name, @RequestParam("password") String password) {
		if (1 == 1) {
			throw new SysException(AuthErrors.USER_TOKEN_EXPIRED);
		}
		return new ResponseEntity<>();
	}
}
