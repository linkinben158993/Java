package com.myclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import com.myclass.connection.JDBCConnection;
import com.myclass.dto.UserDto;
import com.myclass.entity.User;


//Data access object (DAO)
public class UserDAO{
	
	public List<User> findAll() {

		List<User> users = new ArrayList<User>();

		try (Connection conn = JDBCConnection.getConnection()) {
			// BÆ°á»›c 2: Gá»­i cÃ¢u truy váº¥n
			// Táº¡o ra cÃ¢u truy váº¥n phÃ¹ há»£p vá»›i há»‡ quáº£n trá»‹ CSDL mysql
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM users");
			// Thá»±c thi truy váº¥n láº¥y dá»¯u liá»‡u
			ResultSet rs = statement.executeQuery();

			// BÆ°á»›c 3: Xá»­ kÃ½ káº¿t quáº£ tráº£ vá»�
			while (rs.next()) {
				// Táº¡o entity User há»©ng dá»¯ liá»‡u má»—i dÃ²ng tráº£ vá»� tá»« database
				User user = new User();
				// Set thuá»™c tÃ­nh cho User entity
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setFullname(rs.getString("fullname"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleId(rs.getInt("role_id"));
				// Add User vÃ o danh sÃ¡ch Ä‘á»ƒ tráº£ vá»� cho jsp
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public User findById(int id) {

		User user = new User();

		try (Connection conn = JDBCConnection.getConnection()) {
			// BÆ°á»›c 2: Gá»­i cÃ¢u truy váº¥n
			// Táº¡o ra cÃ¢u truy váº¥n phÃ¹ há»£p vá»›i há»‡ quáº£n trá»‹ CSDL mysql
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
			statement.setInt(1, id);

			// Thá»±c thi truy váº¥n láº¥y dá»¯u liá»‡u
			ResultSet rs = statement.executeQuery();

			// BÆ°á»›c 3: Xá»­ kÃ½ káº¿t quáº£ tráº£ vá»�
			while (rs.next()) {
				// Set thuá»™c tÃ­nh cho User entity
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setFullname(rs.getString("fullname"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleId(rs.getInt("role_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public int add(User user) {

		String query = "INSERT INTO Users(email, password, fullname, avatar, role_id) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = JDBCConnection.getConnection()) {
			// BÆ°á»›c 2: Gá»­i cÃ¢u truy váº¥n
			// Táº¡o ra cÃ¢u truy váº¥n phÃ¹ há»£p vá»›i há»‡ quáº£n trá»‹ CSDL mysql
			PreparedStatement statement = conn.prepareStatement(query);
			// Set dá»¯ liá»‡u cho cÃ¢u truy váº¥n
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFullname());
			
			statement.setString(4, user.getAvatar());
			//statement.setString(4, user.getFile_image());
			//Test lấy file hình ảnh
			//statement.setBlob(4, user.getAvatar_test().getInputStream());
			//System.out.println(user.getAvatar_test().getName());			
			
			statement.setInt(5, user.getRoleId());

			
			// Tráº£ vá»� káº¿t quáº£ truy váº¥n
			return statement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int update(User user) {
		String query = "UPDATE users SET email = ?, password = ?, fullname = ?, avatar = ?, role_id = ? WHERE id = ?";
		try (Connection conn = JDBCConnection.getConnection()) {

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFullname());
			statement.setString(4, user.getAvatar());
			statement.setInt(5, user.getRoleId());
			statement.setInt(6, user.getId());

			// Thá»±c thi truy váº¥n láº¥y dá»¯u liá»‡u
			int result = statement.executeUpdate();

			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return - 1;
	}
	
	public int delete(int id) {
		String query = "DELETE FROM users WHERE id = ?";
		try (Connection conn = JDBCConnection.getConnection()) {

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);

			// Thá»±c thi truy váº¥n láº¥y dá»¯u liá»‡u
			int result = statement.executeUpdate();

			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return - 1;
	}
	
	public UserDto findByIdWithDescription(int id) {

		UserDto user = new UserDto();

		try (Connection conn = JDBCConnection.getConnection()) {
			// BÆ°á»›c 2: Gá»­i cÃ¢u truy váº¥n
			// Táº¡o ra cÃ¢u truy váº¥n phÃ¹ há»£p vá»›i há»‡ quáº£n trá»‹ CSDL mysqln
			PreparedStatement statement = conn.prepareStatement("SELECT u.id, u.role_id, u.email, u.fullname, u.password, u.avatar, r.description FROM users u " + 
					"JOIN roles r ON u.role_id = r.id where u.id = ?");
			statement.setInt(1, id);

			// Thá»±c thi truy váº¥n láº¥y dá»¯u liá»‡u
			ResultSet rs = statement.executeQuery();

			// BÆ°á»›c 3: Xá»­ kÃ½ káº¿t quáº£ tráº£ vá»�
			while (rs.next()) {
				// Set thuá»™c tÃ­nh cho User entity
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				//user.setPassword(rs.getString("password"));
				user.setFullname(rs.getString("fullname"));
				user.setAvatar(rs.getString("avatar"));
				user.setRole_id(rs.getInt("role_id"));
				user.setRoleDescription(rs.getString("description"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<UserDto> findAllWithRole() {

		List<UserDto> users = new ArrayList<UserDto>();
		String query = "SELECT u.id, u.email, u.fullname, r.description, r.name FROM users u " + 
				"JOIN roles r ON u.role_id = r.id";
		try (Connection conn = JDBCConnection.getConnection()) {
			
			// BÆ°á»›c 2: Gá»­i cÃ¢u truy váº¥n
			// Táº¡o ra cÃ¢u truy váº¥n phÃ¹ há»£p vá»›i há»‡ quáº£n trá»‹ CSDL mysql
			PreparedStatement statement = conn.prepareStatement(query);
			
			// Thá»±c thi truy váº¥n láº¥y dá»¯u liá»‡u
			ResultSet rs = statement.executeQuery();

			// BÆ°á»›c 3: Xá»­ kÃ½ káº¿t quáº£ tráº£ vá»�
			while (rs.next()) {
				// Táº¡o User DTO há»©ng dá»¯ liá»‡u má»—i dÃ²ng tráº£ vá»� tá»« database
				UserDto user = new UserDto();
				// Set thuá»™c tÃ­nh cho User DTO
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setFullname(rs.getString("fullname"));
				user.setRoleName(rs.getString("name"));
				user.setRoleDescription(rs.getString("description"));
				
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public List<UserDto> findAllByIdWithJobAndStatus(int id) {
		List <UserDto> users = new ArrayList<UserDto>();
		String query = "select u.fullname, u.email, t.start_date, t.end_date, t.name, s.name, t.status_id from users u join tasks t on u.id = t.user_id \r\n" + 
				"		join status s on t.status_id = s.id\r\n" + 
				"        where u.id = ?";
		try (Connection conn = JDBCConnection.getConnection()){
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			
			ResultSet res = statement.executeQuery();
			
			while(res.next()) {
				UserDto user = new UserDto();
				user.setFullname(res.getString("fullname"));
				user.setEmail(res.getString("email"));
				user.setJobName(res.getString("t.name"));
				user.setStartDate(res.getDate("start_date"));
				user.setEndDate(res.getDate("end_date"));
				user.setStatusId(res.getInt("status_id"));
				user.setStatusName(res.getString("s.name"));
				/*
				 * System.out.println(res.getString("fullname"));
				 * System.out.println(res.getString("email"));
				 * System.out.println(res.getString("t.name"));
				 * System.out.println(res.getString("s.name"));
				 * System.out.println(res.getDate("start_date"));
				 * System.out.println(res.getDate("end_date"));
				 * System.out.println(res.getInt("status_id"));
				 */
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
}
