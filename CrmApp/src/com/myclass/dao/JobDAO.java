package com.myclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.JDBCConnection;
import com.myclass.entity.Job;
import com.myclass.entity.Task;

public class JobDAO {
	public List<Job> findAll(){
		List<Job> jobs = new ArrayList<Job>();
		String query =  "select * from jobs";
		try (Connection conn = JDBCConnection.getConnection()){ 
			
			PreparedStatement statement = conn.prepareStatement(query);
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Job job = new Job();
				job.setId(rs.getInt("id"));
				job.setName(rs.getString("name"));
				job.setStartDate(rs.getDate("start_date"));
				job.setEndDate(rs.getDate("end_date"));				
				jobs.add(job);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobs;		
	}
	
	public Job findById(int id) {
		Job job = new Job();

		try (Connection conn = JDBCConnection.getConnection()) {
			//Gởi yêu cầu truy vấn
			//Tạo câu truy vấn
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM jobs WHERE id = ?");
			statement.setInt(1, id);

			// Thá»±c thi truy váº¥n láº¥y dá»¯u liá»‡u
			ResultSet rs = statement.executeQuery();

			// BÆ°á»›c 3: Xá»­ kÃ½ káº¿t quáº£ tráº£ vá»�
			while (rs.next()) {
				// Set thuá»™c tÃ­nh cho User entity
				job.setId(rs.getInt("id"));
				job.setName(rs.getString("name"));
				job.setStartDate(rs.getDate("start_date"));
				job.setEndDate(rs.getDate("end_date"));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return job;
	}
	
	public int add(Job job) {
		String query = "insert into jobs(name, start_date, end_date) values (?, ?, ?)";
		try(Connection conn = JDBCConnection.getConnection()){
			PreparedStatement statement = conn.prepareStatement(query);
			
			statement.setString(1, job.getName());
			statement.setDate(2, job.getStartDate());
			statement.setDate(3, job.getEndDate());
			
			
			return statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	public int update(Job job) {
		String query = "UPDATE jobs SET name = ?, start_date = ?, end_date = ? WHERE id = ?";
		try (Connection conn = JDBCConnection.getConnection()) {

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, job.getName());
			statement.setDate(2, job.getStartDate());
			statement.setDate(3, job.getEndDate());
			statement.setInt(4, job.getId());

			System.out.println(job.getStartDate());
			System.out.println(job.getEndDate());
			
			
			// Thá»±c thi truy váº¥n láº¥y dá»¯u liá»‡u
			int result = statement.executeUpdate();

			System.out.println(result);
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return - 1;
	}

	
	public List<Task> findAllTask(){
		List<Task> tasks = new ArrayList<Task>();
		String query = "select * from tasks";
		try (Connection conn = JDBCConnection.getConnection()){
			PreparedStatement statement = conn.prepareStatement(query);
			
			ResultSet res = statement.executeQuery();
			
			while(res.next()) {
				Task task = new Task();
				task.setName(res.getString("name"));
				task.setStartDate(res.getDate("start_date"));
				task.setEndDate(res.getDate("end_date"));
				task.setUserId(res.getInt("user_id"));
				task.setStatusId(res.getInt("status_id"));
				
				tasks.add(task);
				
				
				System.out.println(res.getString("name"));
				System.out.println(res.getDate("start_date"));
				System.out.println(res.getDate("end_date"));
				System.out.println(res.getInt("user_id"));
				System.out.println(res.getInt("status_id"));
				
			}
		}
		catch(Exception e) {
			
		}
		return tasks;
	}
	
	public int delete(int id) {
		String query = "DELETE FROM jobs WHERE id = ?";
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
