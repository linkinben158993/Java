package com.myclass.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myclass.dto.UserLogin;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("api")
public class LoginApiController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("login")
	public ResponseEntity<String> login(@RequestBody UserLogin userLogin) {
		//Date now = new Date();
		// Bước 1: Thực hiện đăng nhập

		System.out.println(userLogin.getEmail());
		System.out.println(userLogin.getEmail());
		
		Authentication authenticationToken = new UsernamePasswordAuthenticationToken(userLogin.getEmail(),
				userLogin.getPassword());
		
		System.out.println(authenticationToken);
		
		try {
			
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			//Trả về thông tin người dùng.
//			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			
			//Tạo token
//			String token = Jwts.builder()
//					.setSubject(userDetails.getUsername())
//					.setIssuedAt(now) //Thời điểm phát hành
//					.setExpiration(new Date(now.getTime()  + 864000000L)) //Thời gian tồn tại
//					.signWith(SignatureAlgorithm.HS512, "SECRET") //Thuật toán giải mã
//					.compact();
			String token = generateToken(authentication);
			System.out.println(token);
			
			return new ResponseEntity<String>(token, HttpStatus.OK);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<String>("Sai tên đăng nhập hoặc mật khẩu!", HttpStatus.BAD_REQUEST);
	}
	
	private String generateToken(Authentication authentication) {
		final String JWT_SECRET = "SECRET";
		
		final long JWT_EXPIRATION = 864000000L;
		
		Date now = new Date();
		
		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		System.out.println(userDetails.getUsername());
		
		String token = Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setIssuedAt(now) //Thời điểm phát hành
				.setExpiration(expiryDate) //Thời gian tồn tại
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET) //Thuật toán giải mã
				.compact();
		
		return token;
	}
}
