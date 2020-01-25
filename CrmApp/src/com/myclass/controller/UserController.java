package com.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.constants.UrlConstants;
import com.myclass.dao.RoleDAO;
import com.myclass.dao.UserDAO;
import com.myclass.dto.UserDto;
import com.myclass.entity.User;


//"/user","/manager/user", "/manager/user/add", "/manager/user/edit", "/manager/user/delete", "/manager/user/details"

@WebServlet(name = "UserController", 
	urlPatterns = {
			UrlConstants.USER, UrlConstants.USER_DETAILS,
			UrlConstants.ADMIN_USER_ADD,UrlConstants.ADMIN_USER_DELETE,UrlConstants.ADMIN_USER_EDIT,UrlConstants.ADMIN_USER_LIST, 
			UrlConstants.ADMIN_USER_DETAILS, 
			UrlConstants.MANAGER_USER_ADD, UrlConstants.MANAGER_USER_DELETE,UrlConstants.MANAGER_USER_EDIT, UrlConstants.MANAGER_USER_LIST,
			UrlConstants.MANAGER_USER_DETAILS
})
public class UserController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private UserDAO userDAO = null;
	private RoleDAO roleDAO = null;
	
	public UserController() {
		userDAO = new UserDAO();
		roleDAO = new RoleDAO();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("USER_LOGIN") == null) {
			resp.sendRedirect(req.getContextPath() + "/login?error=1");
			return;
		}
		
		else {
			String action = req.getServletPath();
			
			switch (action) {
			
			//Đưa ra danh sánh người dùng 
			case UrlConstants.USER:
			case UrlConstants.ADMIN_USER_LIST:
			case UrlConstants.MANAGER_USER_LIST:
			{
				req.setAttribute("users", userDAO.findAllWithRole());
				req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
				break;
			}
			
			//Xem chi tiết 1 người dùng
			case UrlConstants.USER_DETAILS:
			case UrlConstants.MANAGER_USER_DETAILS:
			case UrlConstants.ADMIN_USER_DETAILS:
			{
				req.setCharacterEncoding("UTF-8");
				int id = Integer.parseInt(req.getParameter("id"));
				req.setAttribute("user", userDAO.findByIdWithDescription(id));
				req.setAttribute("user_jobs", userDAO.findAllByIdWithJobAndStatus(id));
				List<UserDto> users = userDAO.findAllByIdWithJobAndStatus(id);
				
				for(UserDto user : users) {
					System.out.println(user.getFullname());
					System.out.println(user.getStartDate());
					System.out.println(user.getEndDate());
					System.out.println(user.getEmail());
					System.out.println(user.getJobName());
					System.out.println(user.getStatusName());
				}
				
				req.getRequestDispatcher("/views/user/details.jsp").forward(req, resp);
				break;
			}
			
			//Thêm mới người dùng 
			case UrlConstants.ADMIN_USER_ADD:
			{
				getAdd(req,resp);
				break;
			}
			
			//Chỉnh sửa người dùng
			case UrlConstants.ADMIN_USER_EDIT:
			{
				getEdit(req,resp);
				break;
			}
			
			//Xóa người dùng
			case UrlConstants.ADMIN_USER_DELETE:
			{
				getDelete(req, resp);
				break;
			}
			
			
			default:
				break;
			}
		}		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String action = req.getServletPath();
			switch(action) {
			
			case UrlConstants.ADMIN_USER_ADD:
			{
				postAdd(req, resp);
				break;
			}
			
			case UrlConstants.ADMIN_USER_EDIT:
			{
				postEdit(req, resp);
				break;
			}
			default:
				break;
	}
		
	}
	private void getAdd(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setAttribute("roles", roleDAO.findAll());
		req.getRequestDispatcher("/views/user/add.jsp").forward(req, resp);
	}
	private void postAdd(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		req.setCharacterEncoding("UTF-8");
		
		int roleId = Integer.parseInt(req.getParameter("roleId"));
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String avatar = req.getParameter("avatar");
		
		
		//Test lấy avatar file
	
		/*
		 * InputStream inputStream = null; Part filePart = req.getPart("avatar_test");
		 * if(filePart != null) { System.out.println(filePart.getName());
		 * System.out.println(filePart.getSize());
		 * System.out.println(filePart.getContentType());
		 * 
		 * inputStream = filePart.getInputStream(); }
		 */
		
		
		//String file_image = req.getParameter("file_image");
		
		String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
		
		// KIá»‚M TRA Dá»® LIá»†U
		if (fullname == null || fullname.isEmpty()) {
			req.setAttribute("message", "Input Name!");
			req.getRequestDispatcher("/views/user/add.jsp").forward(req, resp);
			return;
		}
		
		if (email == null || email.isEmpty()) {
			req.setAttribute("message", "Input Email!");
			req.getRequestDispatcher("/views/user/add.jsp").forward(req, resp);
			return;
		}
		
		if (password == null || password.isEmpty()) {
			req.setAttribute("message", "Input Password!");
			req.getRequestDispatcher("/views/user/add.jsp").forward(req, resp);
			return;
		}
		
		// Táº¡o má»›i 1 thá»ƒ hiá»‡n cá»§a Role entity
		// Ä?áº©y dá»¯ liá»‡u vÃ o Ä‘á»ƒ gá»­i lÃªn hÃ m add cá»§a RoleDao
		
		
		//Test lấy avatar
		//User user = new User(email, hashed, fullname, avatar, roleId, file_image);
		
		User user = new User(email, hashed, fullname, avatar, roleId);
		
		// Gá»?i hÃ m add cá»§a RoleDAO Ä‘á»ƒ thÃªm má»›i
		int result = userDAO.add(user);

		// TrÆ°á»?ng há»£p thÃªm má»›i tháº¥t báº¡i
		if (result < 1) {
			req.setAttribute("message", UrlConstants.ADD_FAILED);
			req.getRequestDispatcher("/views/user/add.jsp").forward(req, resp);
			return;
		} else {
			req.setAttribute("message", UrlConstants.ADD_SUCCESS);
			req.setAttribute("users", userDAO.findAllWithRole());
			req.getRequestDispatcher("/views/user/index.jsp").forward(req,resp);
			return;
		}

	}
	
	private void getEdit(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {


		int id = Integer.parseInt(req.getParameter("id"));
		
		User user = userDAO.findById(id);

		// BÆ°á»›c 3: Chuyá»ƒn role vÆ°a láº¥y tá»« db vá»� cho jsp Ä‘á»ƒ show thÃ´ng tin lÃªn form
		req.setAttribute("roles", roleDAO.findAll());
		req.setAttribute("user", user);
		req.setAttribute("id", id);
		req.getRequestDispatcher("/views/user/edit.jsp").forward(req, resp);
	}

	private void postEdit(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		
		req.setCharacterEncoding("UTF-8");
		
		int id = Integer.parseInt(req.getParameter("id"));
		int roleId = Integer.parseInt(req.getParameter("roleId"));
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String avatar = req.getParameter("avatar");

		String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
		
		User user = new User();
		
		user.setFullname(fullname);
		user.setEmail(email);
		user.setRoleId(roleId);
		user.setId(id);
		user.setPassword(hashed);
		user.setAvatar(avatar);
		
		int result = userDAO.update(user);
		
		System.out.println(result);
		
		if(result < 1) {
			req.setAttribute("message", UrlConstants.EDIT_FAILED);
			req.getRequestDispatcher("/views/user/edit.jsp").forward(req, resp);
		}
		else {
			req.setAttribute("message", UrlConstants.EDIT_SUCCESS);
			req.setAttribute("users", userDAO.findAllWithRole());
			req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
			return;
		}
	}
	
	private void getDelete(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException, ServletException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		userDAO.delete(id);
		//resp.sendRedirect(req.getContextPath() + UrlConstants.USER);
		req.setAttribute("message", UrlConstants.DELETE_SUCCESS);
		req.setAttribute("users", userDAO.findAllWithRole());
		req.getRequestDispatcher("/views/user/index.jsp").forward(req, resp);
	}
}
