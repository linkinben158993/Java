package com.myclass.entity;

import javax.servlet.http.Part;

public class User {
	private int id;
	private String email;
	private String password;
	private String fullname;
	private String avatar;
	private int roleId;
	private String file_image;
	//Test lấy file hình ảnh
	private Part avatar_test;
	
	public Part getAvatar_test() {
		return avatar_test;
	}

	public void setAvatar_test(Part avatar_test) {
		this.avatar_test = avatar_test;
	}

	public String getFile_image() {
		return file_image;
	}

	public void setFile_image(String file_image) {
		this.file_image = file_image;
	}

	public User() {}

	public User(int id, String email, String password, String fullname, String avatar, int roleId) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.roleId = roleId;
	}

	public User(String email, String password, String fullname, String avatar, int roleId) {
		super();
		//this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.roleId = roleId;
	}

	
	public User(String email, String password, String fullname, String avatar, int roleId, String file_image) {
		super();
		//this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.roleId = roleId;
		this.file_image = file_image;
	}
	
	//Test lấy file hình ảnh
	public User(String email, String password, String fullname, String avatar, int roleId, Part avatar_test) {
		super();
		//this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.avatar = avatar;
		this.roleId = roleId;
		this.avatar_test = avatar_test;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
