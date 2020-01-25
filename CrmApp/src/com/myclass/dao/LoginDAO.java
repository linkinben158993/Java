package com.myclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.myclass.connection.JDBCConnection;
import com.myclass.dto.UserDto;

public class LoginDAO {
	//private final String query = "SELECT * FROM users WHERE email = ?";
	private final String FIND_BY_EMAIL = "SELECT u.id, u.email, u.fullname, u.password, u.role_id, r.name FROM users u\r\n" + 
			"JOIN roles r ON u.role_id = r.id where email = ?";
	public UserDto findbyEmail(String email){
		UserDto user = new UserDto();

		try (Connection conn = JDBCConnection.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(FIND_BY_EMAIL);
			statement.setString(1, email);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setFullname(rs.getString("fullname"));
				//user.setAvatar(rs.getString("avatar"));
				user.setRole_id(rs.getInt("role_id"));
				user.setRoleName(rs.getString("name"));
			}
		} catch (Exception e) {
			user = null;
			e.printStackTrace();
		}
		return user;
	}
}
