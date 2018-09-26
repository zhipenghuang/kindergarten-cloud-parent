package com.zc.kindergarten.auth.server.controller;

import com.zc.kindergarten.auth.server.service.AuthService;
import com.zc.kindergarten.auth.server.vo.request.JwtAuthenticationRequest;
import com.zc.kindergarten.common.msg.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @author hzp
 * @create 2018/9/19.
 */
@RestController
@RequestMapping("jwt")
@Slf4j
public class AuthController {

	@Value("${jwt.token-header}")
	private String tokenHeader;

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "token", method = RequestMethod.POST)
	public ResponseEntity<String> createAuthenticationToken(
			@RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {
		log.info(authenticationRequest.getUsername()+" require logging...");
		final String token = authService.login(authenticationRequest);
		return new ResponseEntity<>(token);
	}

	@RequestMapping(value = "refresh", method = RequestMethod.GET)
	public ResponseEntity<String> refreshAndGetAuthenticationToken(
			HttpServletRequest request) throws Exception {
		String token = request.getHeader(tokenHeader);
		String refreshedToken = authService.refresh(token);
		return new ResponseEntity<>(refreshedToken);
	}

	@RequestMapping(value = "verify", method = RequestMethod.GET)
	public ResponseEntity<?> verify(String token) throws Exception {
		authService.validate(token);
		return new ResponseEntity<>();
	}
}
