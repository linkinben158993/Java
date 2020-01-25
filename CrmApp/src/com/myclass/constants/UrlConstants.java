package com.myclass.constants;

public class UrlConstants {
	
	//Các tên miền được vào với mọi quyền, kể các người dùng khách
	public final static String HOME = "/home";
	public final static String ERROR = "/error";
	public final static String LOGIN = "/login";
	public final static String LOGOUT = "/logout";
	
	//Role
	public final static String ROLE = "/role";
	public final static String ADMIN_ROLE_LIST = "/admin/role";
	public final static String ADMIN_ROLE_ADD = "/admin/role/add";
	public final static String ADMIN_ROLE_EDIT = "/admin/role/edit";
	public final static String ADMIN_ROLE_DELETE = "/admin/role/delete";
	
	public final static String MANAGER_ROLE_LIST = "/manager/role";
	

	
	
	//User List
	public final static String USER = "/user";
	public final static String USER_ROLE_LIST = "/user/role";
	public final static String USER_JOB_LIST = "/user/job";
	public final static String USER_SELF_EDIT = "/user/edit";
	
	
	public final static String USER_DETAILS = "/user/details";
	public final static String MANAGER_USER_LIST = "/manager/user";
	public final static String MANAGER_USER_ADD = "/manager/user/add";
	public final static String MANAGER_USER_EDIT = "/manager/user/edit";
	public final static String MANAGER_USER_DELETE = "/manager/user/delete";
	public final static String MANAGER_USER_DETAILS = "/manager/user/details";
	
	public final static String ADMIN_USER_LIST = "/admin/user";
	public final static String ADMIN_USER_ADD = "/admin/user/add";
	public final static String ADMIN_USER_EDIT = "/admin/user/edit";
	public final static String ADMIN_USER_DELETE = "/admin/user/delete";
	public final static String ADMIN_USER_DETAILS = "/admin/user/details";
	
	//Job
	public final static String JOB = "/job";	
	
	public final static String ADMIN_JOB_LIST = "/admin/job";
	
	public final static String MANAGER_JOB_LIST = "/manager/job";
	public final static String MANAGER_JOB_ADD = "/manager/job/add";
	public final static String MANAGER_JOB_EDIT = "/manager/job/edit";
	public final static String MANAGER_JOB_DELETE = "/manager/job/delete";
	public final static String MANAGER_JOB_DETAILS = "/manager/job/details";
	
	//Notification
	public final static String ADD_SUCCESS = "Add Success!";
	public final static String ADD_FAILED = "Add Failed!";
	public final static String EDIT_SUCCESS = "Edit Success!";
	public final static String EDIT_FAILED = "Edit Failed!";
	public final static String DELETE_SUCCESS = "Delete Success!";
	public final static String DELETE_FAILED = "Delete Failed";
	
		//Error
	public final static String ERROR_MESSAGE = "Please Sign In For Further Viewing!";
	public final static String ERROR_UNKNOWN_USER = "User Unknown!";
	public final static String ERROR_WRONG_PASSWORD = "WRONG PASSWORD!";
	public final static String ERROR_INPUT_MISSING = "Please Fill In All The Form!";
	

	
}
