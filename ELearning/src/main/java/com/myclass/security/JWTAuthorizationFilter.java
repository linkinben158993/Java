package com.myclass.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	@Autowired
	private UserDetailsService _userDetailsService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this._userDetailsService = userDetailsService;
	}

	//Thử API Postman or Swagger
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//Chuỗi bất kì
		final String JWT_SECRET = "SECRET";
		
		// Lấy value của header với key Authorization
		String headerAuthorization = request.getHeader("Authorization");
		
		System.out.println(headerAuthorization);

		// Kiểm tra header có giá trị chưa?
		if (!headerAuthorization.isEmpty() && headerAuthorization.startsWith("Bearer ")) {
			// Lấy ra chuỗi token
			String token = headerAuthorization.replace("Bearer ", "");
			
			System.out.println(token);

			// Giải mã token để lấy email
			String email = Jwts.parser()
							   .setSigningKey(JWT_SECRET)
							   .parseClaimsJws(token)
							   .getBody()
							   .getSubject();
			
			System.out.println(email);
			// Lấy thông tin user từ DB
			UserDetails userDetails = _userDetailsService.loadUserByUsername(email);

			// Người dùng hợp lệ thì set vào security context
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authenticationToken);

			System.out.println(authenticationToken);
		}
		chain.doFilter(request, response);
	}
}
