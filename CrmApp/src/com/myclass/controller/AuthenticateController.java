package com.myclass.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.constants.UrlConstants;
import com.myclass.dao.LoginDAO;
import com.myclass.dto.UserDto;

@WebServlet(name ="AuthenticateController", urlPatterns = {UrlConstants.LOGIN,UrlConstants.LOGOUT})
public class AuthenticateController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	LoginDAO loginDAO = null;
	
	public AuthenticateController() {
		loginDAO = new LoginDAO();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		
		switch(action) {
		case UrlConstants.LOGIN:
			String error = req.getParameter("error");
			if(error == null) {
				req.getRequestDispatcher("/views/login/index.jsp").forward(req, resp);
				break;
			}
			else if(!error.isEmpty() && error != null && error.equals("0")) {
				req.setAttribute("message", UrlConstants.ERROR_MESSAGE);
				req.getRequestDispatcher("/views/login/index.jsp").forward(req, resp);
				break;
			}
			else if(!error.isEmpty() && error != null && error.equals("1")) {
				req.setAttribute("message", UrlConstants.ERROR_MESSAGE);
				req.getRequestDispatcher("/views/login/index.jsp").forward(req, resp);
				break;
			}
			else if(!error.isEmpty() && error != null && error.equals("2")) {
				req.setAttribute("message", UrlConstants.ERROR_MESSAGE);
				req.getRequestDispatcher("/views/login/index.jsp").forward(req, resp);
				break;
			}
			else if(!error.isEmpty() && error != null && error.equals("3")) {
				req.setAttribute("message", UrlConstants.ERROR_MESSAGE);
				req.getRequestDispatcher("/views/login/index.jsp").forward(req, resp);
				break;
			}
			break;
		case UrlConstants.LOGOUT:
			logOut(req, resp);
			resp.sendRedirect(req.getContextPath() +UrlConstants.LOGIN);
			break;
		case "/error":{
			req.getRequestDispatcher("/views/error/403.jsp").forward(req, resp);
			break;
		}
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		if(action.equals("/login")) {
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			UserDto user = loginDAO.findbyEmail(email);
			
			if(email.isEmpty() || password.isEmpty()) {
				req.setAttribute("message", UrlConstants.ERROR_INPUT_MISSING);
				req.getRequestDispatcher("/views/login/index.jsp").forward(req, resp);
				return;
			}
			
			else if(user.getEmail() == null) {
				req.setAttribute("message", UrlConstants.ERROR_UNKNOWN_USER);
				req.getRequestDispatcher("/views/login/index.jsp").forward(req, resp);
				return;
			}
			
			else if(BCrypt.checkpw(password, user.getPassword())){
				//Khởi tạo session
				HttpSession session = req.getSession();
				//Lưu user vào session có tên là USER_LOGIN
				session.setAttribute("USER_LOGIN", user);
				resp.sendRedirect(req.getContextPath() + "/home");
				return;
			}
					
			else {
				req.setAttribute("message", UrlConstants.ERROR_WRONG_PASSWORD);
				req.getRequestDispatcher("/views/login/index.jsp").forward(req, resp);
				return;
			}
		}
	}
	
	private void logOut(HttpServletRequest req, HttpServletResponse resp) throws IOException{		
		HttpSession session = req.getSession();
		session.removeAttribute("USER_LOGIN");	
		//resp.sendRedirect(req.getContextPath() + "/logout?success=1");
	}
}
