package com.myclass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Entity(name = "users")
public class Users extends GenericEntities<String>{
	@Id
	private String id;
	@NotBlank(message = "Vui lòng nhập địa chỉ email!")
	@Email(message = "Vui lòng nhập đúng định dạng email!")
	private String email;
	@NotBlank(message = "Vui lòng nhập họ tên!")	
	private String fullname;
	@NotBlank(message = "Vui lòng nhập mật khẩu!")
	@Length(min = 6, message = "Mật khẩu phải trên 6 kí tự!")
	private String password;
	private String avatar;
	private String phone;
	private String address;
	private String website;
	private String facebook;
	
	@Column(name = "role_id")
	@NotBlank(message = "Vui lòng lựa chọn quyền!")
	private String role_id;
	
	@ManyToOne()
	@JoinColumn(name = "role_id", insertable = false, updatable = false, nullable = true)
	private Roles role;

	public Users() {

	}

	public Users(String id, String email, String fullname, String password, String role_id) {
		super();
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.role_id = role_id;
	}
	
	public Users(String id, String email, String fullname, String password, String avatar, String phone, String address,
			String website, String facebook, String role_id) {
		super();
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.avatar = avatar;
		this.phone = phone;
		this.address = address;
		this.website = website;
		this.facebook = facebook;
		this.role_id = role_id;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
}
