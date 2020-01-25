package com.myclass.dto;

import java.sql.Date;

public class JobDto {
	private int id;
	private String name;
	private Date startDate;
	private Date endDate;
	private int status_id;
	private int user_id;
	private int job_id;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public int getStatus_id() {
		return status_id;
	}
	
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	
	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public int getJob_id() {
		return job_id;
	}
	
	public void setJob_id(int job_id) {
		this.job_id = job_id;
	}
	
	public JobDto(int id, String name, Date startDate, Date endDate, int status_id, int user_id, int job_id) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status_id = status_id;
		this.user_id = user_id;
		this.job_id = job_id;
	}

	public JobDto() {
	}

	
}
