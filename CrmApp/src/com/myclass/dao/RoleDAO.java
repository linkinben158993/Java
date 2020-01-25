package com.myclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.JDBCConnection;
import com.myclass.entity.Role;

//Data access object (DAO)
public class RoleDAO {

	public List<Role> findAll(){
		
		List<Role> roles = new ArrayList<Role>();

		try (Connection conn = JDBCConnection.getConnection()) {
			// BÆ°á»›c 2: Gá»­i cÃ¢u truy váº¥n
			// Táº¡o ra cÃ¢u truy váº¥n phÃ¹ há»£p vá»›i há»‡ quáº£n trá»‹ CSDL mysql
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM roles");
			// Thá»±c thi truy váº¥n láº¥y dá»¯u liá»‡u
			ResultSet rs = statement.executeQuery();

			// BÆ°á»›c 3: Xá»­ kÃ½ káº¿t quáº£ tráº£ vá»�
			while (rs.next()) {
				// Táº¡o entity Role há»©ng dá»¯ liá»‡u má»—i dÃ²ng tráº£ vá»� tá»« database
				Role role = new Role();
				// Set thuá»™c tÃ­nh cho role entity
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				role.setDescription(rs.getString("description"));
				// Add role vÃ o danh sÃ¡ch Ä‘á»ƒ tráº£ vá»� cho jsp
				roles.add(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roles;
	}
	
	public Role findById(int id) {
		
		Role role = new Role();
		
		try (Connection conn = JDBCConnection.getConnection()) {
			// BÆ°á»›c 2: Gá»­i cÃ¢u truy váº¥n
			// Táº¡o ra cÃ¢u truy váº¥n phÃ¹ há»£p vá»›i há»‡ quáº£n trá»‹ CSDL mysql
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM roles WHERE id = ?");
			statement.setInt(1, id);

			// Thá»±c thi truy váº¥n láº¥y dá»¯u liá»‡u
			ResultSet rs = statement.executeQuery();
			
			// BÆ°á»›c 3: Xá»­ kÃ½ káº¿t quáº£ tráº£ vá»�
			while (rs.next()) {
				// Set thuá»™c tÃ­nh cho role entity
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				role.setDescription(rs.getString("description"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

	public int add(Role role) {
		
		String query = "INSERT INTO roles(name, description) VALUES (?, ?)";
		try (Connection conn = JDBCConnection.getConnection()) {
			// BÆ°á»›c 2: Gá»­i cÃ¢u truy váº¥n
			// Táº¡o ra cÃ¢u truy váº¥n phÃ¹ há»£p vá»›i há»‡ quáº£n trá»‹ CSDL mysql
			PreparedStatement statement = conn.prepareStatement(query);
			// Set dá»¯ liá»‡u cho cÃ¢u truy váº¥n
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());

			// Tráº£ vá»� káº¿t quáº£ truy váº¥n
			return statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int update(Role role) {
		String query = "UPDATE roles SET name = ?, description = ? WHERE id = ?";
		try (Connection conn = JDBCConnection.getConnection()) {

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());
			statement.setInt(3, role.getId());

			// Thá»±c thi truy váº¥n láº¥y dá»¯u liá»‡u
			int result = statement.executeUpdate();

			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return - 1;
	}
	
	public int delete(int id) {
		String query = "DELETE FROM roles WHERE id = ?";
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
}
