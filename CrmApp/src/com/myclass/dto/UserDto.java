package com.myclass.dto;

import java.sql.Date;

public class UserDto {
	private int id;
	private int role_id;
	private String email;
	private String password;
	private String fullname;
	private String avatar;
	
	//Cần để chọn role cho người dùng lúc thêm mới
	private String roleName;
	private String roleDescription;
	
	//Cần để chọn ra công việc mà người dùng đang làm
	private String jobName;
	private String jobStatus;
	private Date startDate;
	private Date endDate;
	private int statusId;
	private String statusName;
	
	public UserDto() {
		
	}

	public UserDto(int id, String email, String password, String fullname, String avatar, String roleName,
			String roleDescription) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.roleName = roleName;
		this.roleDescription = roleDescription;
	}
	
	public UserDto(int id, String email, String fullname, String jobName, String jobStatus, Date start_date, Date end_date, int statusId, String statusName) {
		super();
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.jobName = jobName;
		this.jobStatus = jobStatus;
		this.startDate = start_date;
		this.endDate = end_date;
		this.statusId = statusId;
		this.statusName = statusName;
	}
	
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
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

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public UserDto(int id, String email, String fullname, String roleName) {
		super();
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.roleName = roleName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDescription() {
		return this.roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
}
