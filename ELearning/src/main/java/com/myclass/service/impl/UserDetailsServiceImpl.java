package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myclass.dto.CustomUserDetails;
import com.myclass.entity.Users;
import com.myclass.repository.UserRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		//Lấy thông tin đăng nhập từ DB
		Users user = userRepository.findByEmail(email);
		System.out.println(email);
		
		if(user.equals(null)) {
			throw new UsernameNotFoundException("Không tìm thấy tên người dùng!");
		}
		
		//Lấy danh sách các quyền từ DB
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		String roleName = user.getRole().getName();
		System.out.println(roleName);
		authorities.add(new SimpleGrantedAuthority(roleName));
		
		System.out.println(user.getFullname());
		
		CustomUserDetails userDetails = 
				new CustomUserDetails(user.getEmail(), user.getPassword(), authorities);
		
		//Set các đối field cần thiết nếu cần show lên html vào đây.
		userDetails.setFullname(user.getFullname());
		userDetails.setId(user.getId());
		
		//Trả về đổi tượng chứa email mật khẩu và tên quyền, tên quyền đặt dưới định dạnh ROLE_<Tên quyền> trong DB
		return userDetails;
	}

}
