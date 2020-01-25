package com.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.constants.UrlConstants;
import com.myclass.dao.RoleDAO;
import com.myclass.entity.Role;

@WebServlet(name = "RoleServlet", urlPatterns = {
		UrlConstants.USER_ROLE_LIST, UrlConstants.MANAGER_ROLE_LIST, UrlConstants.ADMIN_ROLE_LIST,
		UrlConstants.ADMIN_ROLE_ADD, UrlConstants.ADMIN_ROLE_EDIT, UrlConstants.ADMIN_ROLE_DELETE })
public class RoleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RoleDAO roleDAO = null;

	public RoleController() {
		roleDAO = new RoleDAO();
	}

	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("USER_LOGIN") == null) {
			resp.sendRedirect(req.getContextPath() + "/login?error=2");
			return;
		}
		else {
			String action = req.getServletPath();
			
			switch (action) {
			case UrlConstants.USER_ROLE_LIST:
			case UrlConstants.MANAGER_ROLE_LIST:
			case UrlConstants.ADMIN_ROLE_LIST:
				getList(req, resp);
				break;
			case UrlConstants.ADMIN_ROLE_ADD:
				getAdd(req, resp);
				break;
			case UrlConstants.ADMIN_ROLE_EDIT:
				getEdit(req, resp);
				break;
			case UrlConstants.ADMIN_ROLE_DELETE:
				getDelete(req,resp);
				break;
			default:
				break;
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getServletPath();
		switch (action) {
		case UrlConstants.ADMIN_ROLE_ADD:
			postAdd(req, resp);
			break;
		case UrlConstants.ADMIN_ROLE_EDIT:
			postEdit(req, resp);
			break;
		default:
			break;
		}

	}

	private void getList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Role> roles = roleDAO.findAll();
		req.setAttribute("roles", roles);
		req.getRequestDispatcher("/views/role/index.jsp").forward(req, resp);
	}
	
	private void getAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/role/add.jsp").forward(req, resp);
	}
	
	private void getDelete(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		roleDAO.delete(id);
		//resp.sendRedirect(req.getContextPath() + UrlConstants.USER_ROLE_LIST);
	}

	private void getEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// BÆ°á»›c 1: Láº¥y ra id tá»« url
		int id = Integer.parseInt(req.getParameter("id"));

		Role role = roleDAO.findById(id);

		// BÆ°á»›c 3: Chuyá»ƒn role vÆ°a láº¥y tá»« db vá»? cho jsp Ä‘á»ƒ show thÃ´ng tin lÃªn form
		req.setAttribute("role", role);
		req.getRequestDispatcher("/views/role/edit.jsp").forward(req, resp);
	}

	private void postAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		String name = req.getParameter("name");
		String description = req.getParameter("description");

		// KIá»‚M TRA Dá»® LIá»†U
		if (name == null || name.isEmpty()) {
			req.setAttribute("message", "Input Role Name!");
			req.getRequestDispatcher("/views/role/add.jsp").forward(req, resp);
			return;
		}

		// Táº¡o má»›i 1 thá»ƒ hiá»‡n cá»§a Role entity
		// Ä?áº©y dá»¯ liá»‡u vÃ o Ä‘á»ƒ gá»­i lÃªn hÃ m add cá»§a RoleDao
		Role role = new Role();
		role.setName(name);
		role.setDescription(description);

//		System.out.println(name);
//		System.out.println(description);
		
		// Gá»?i hÃ m add cá»§a RoleDAO Ä‘á»ƒ thÃªm má»›i
		int result = roleDAO.add(role);

		// TrÆ°á»?ng há»£p thÃªm má»›i tháº¥t báº¡i
		//Trả về trang thêm mới nếu như thêm mới thất bại.
		if (result < 1) {
			req.setAttribute("message", UrlConstants.ADD_FAILED);
			req.getRequestDispatcher("/views/role/add.jsp").forward(req, resp);
			return;
		} else {
			// Chuyá»ƒn hÆ°á»›ng vá»? trang danh sÃ¡ch sáº£n pháº©m
			//resp.sendRedirect(req.getContextPath() + UrlConstants.USER_ROLE_LIST);
			req.setAttribute("message", UrlConstants.ADD_SUCCESS);
			req.setAttribute("roles", roleDAO.findAll());
			req.getRequestDispatcher("/views/role/index.jsp").forward(req, resp);
			return;
		}
	}

	private void postEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String description = req.getParameter("description");

		Role role = new Role(id, name, description);

		if (roleDAO.update(role) == -1) {
			req.setAttribute("message", UrlConstants.EDIT_FAILED);
			req.getRequestDispatcher("/views/role/edit.jsp").forward(req, resp);
			return;
		}

		// Chuyá»ƒn hÆ°á»›ng vá»? trang danh sÃ¡ch quyá»?n
		//resp.sendRedirect(req.getContextPath() + UrlConstants.USER_ROLE_LIST);
		req.setAttribute("message", UrlConstants.EDIT_SUCCESS);
		req.setAttribute("roles", roleDAO.findAll());
		req.getRequestDispatcher("/views/role/index.jsp").forward(req, resp);
		return;
	}
}
