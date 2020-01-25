package com.myclass.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.constants.UrlConstants;
import com.myclass.dto.UserDto;

@WebFilter(urlPatterns = {UrlConstants.LOGIN,UrlConstants.ERROR,UrlConstants.LOGOUT})
public class AuthenticateFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String action = req.getServletPath();
		
		if(action.equals(UrlConstants.LOGIN) || action.startsWith(UrlConstants.LOGOUT) || action.equals(UrlConstants.HOME) || action.equals(UrlConstants.ERROR)) {
			System.out.println("Users or Guests Enter Home or Error!");
			chain.doFilter(request, response);
			return;
		}
		
		HttpSession session = req.getSession();
		if(session.getAttribute("USER_LOGIN") == null) {
			resp.sendRedirect(req.getContextPath() + "/login?error=0");
			return;
		}
		
		UserDto user = (UserDto) session.getAttribute("USER_LOGIN");
		
		if (action.startsWith("/user") ) {
			switch(action) {
			//Chỉ cần là người dùng là xem được danh sách
			
			//Case User
			case UrlConstants.ADMIN_USER_LIST:
			case UrlConstants.MANAGER_USER_LIST:
			case UrlConstants.USER:
			case UrlConstants.USER_DETAILS:
			
			//Case Role]
			case UrlConstants.ADMIN_ROLE_LIST:
			case UrlConstants.MANAGER_ROLE_LIST:
			case UrlConstants.USER_ROLE_LIST:
			
			//Case Job
			case UrlConstants.JOB:
			case UrlConstants.ADMIN_JOB_LIST:
			case UrlConstants.MANAGER_JOB_LIST:
			case UrlConstants.USER_JOB_LIST:	
			{
				chain.doFilter(req, resp);
			}
			default:{
				break;
			}
			}
		}
		
		//Url bắt đầu bằng manager
		//Những controller manager được phép vào
		else if(action.startsWith("/manager")) {
			
			//Kiểm tra quyền
			switch(action) {

			//Chỉ có Manager điều chỉnh được danh sách công việc
			//Chỉ có Manager được dùng chức năng này					
			//Job
			case UrlConstants.MANAGER_JOB_ADD:
			case UrlConstants.MANAGER_JOB_DELETE:
			case UrlConstants.MANAGER_JOB_EDIT:
				
			//Xem user của manager khác với admin	
			case UrlConstants.MANAGER_JOB_DETAILS:
			{
				if(user.getRoleName().equals("ROLE_MANAGER")) {
					System.out.println("Managers are doing things!");
					System.out.println("Username: "+ user.getFullname());
					System.out.println("Action: " + action);
					chain.doFilter(req, resp);
					break;
				}
				else if(user.getRoleName().equals("ROLE_MANAGER") || user.getRoleName().equals("ROLE_ADMIN")) {
					System.out.println("Managers or Admins are viewing jobs!");
					System.out.println("Username: "+ user.getFullname());
					System.out.println("Action: " + action);
					chain.doFilter(req, resp);
				}
				else {
					System.out.println(user.getRoleName()+"s are acting out of their roles!");
					System.out.println("Username: "+ user.getFullname());
					System.out.println("Action: " + action);
					resp.sendRedirect(req.getContextPath() + UrlConstants.ERROR);
					//req.getRequestDispatcher("/views/error/403.jsp").forward(req, resp);
					break;	
				}
			}
			
			default:
			{
				break;
			}
			}		
		}
		
		//Url kết thúc bằng edit thì chỉ có bản thân edit tài khoản của mình hoặc Admin người xem mới được edit tài khoản của người khác
		else if(action.endsWith("/edit")) {
			switch (action) {
			case UrlConstants.ADMIN_USER_EDIT:
			case UrlConstants.ADMIN_ROLE_EDIT:
			case UrlConstants.USER_SELF_EDIT:
			{
				//Tự edit bản thân mình (Không tính admin do admin sẽ được edit cả người khác
				//Kiểm tra quyền là User hoặc Manager cộng với id của là đúng của người dùng đang đăng nhập thì cho vào controller
				if((user.getRoleName().equals("ROLE_USER") || user.getRoleName().equals("ROLE_MANAGER"))
						&& user.getId() == Integer.parseInt(req.getParameter("id"))) {
					//Thử
					System.out.println("Self Edit of User Entity!");
					System.out.println("Username: "+ user.getFullname());
					System.out.println("Action: " + action);
					chain.doFilter(req, resp);
					break;
				}
				
				//Giới hạn quyền edit của user ở đây
				//User không được edit bất cứ thứ gì khác ngoài profile của mình
				else if(user.getRoleName().equals("ROLE_USER") && (action.startsWith("/admin") || action.startsWith("/manager"))) {
					System.out.println(user.getRoleName()+"s are acting out of their roles!");
					System.out.println("Username: "+ user.getFullname());
					System.out.println("Action: " + action);
					resp.sendRedirect(req.getContextPath() + UrlConstants.ERROR);
					//req.getRequestDispatcher("/views/error/403.jsp").forward(req, resp);
					break;					
				}
				
				//Giới hạn quyền edit của manager ở đây
				//Manager không được edit quyền với thành viên
				else if(user.getRoleName().equals("ROLE_MANAGER") && action.startsWith("/admin")) {
					System.out.println("Users are trying to edit something out of their roles!");
					resp.sendRedirect(req.getContextPath() + UrlConstants.ERROR);
					//req.getRequestDispatcher("/views/error/403.jsp").forward(req, resp);
					break;
				}
				
				//Giới hạn quyền edit của admin ở đây
				//Chỉ có manager được vào edit của công việc
				else if(user.getRoleName().equals("ROLE_ADMIN") && action.startsWith("/manager")) {
					System.out.println(user.getRoleName()+"s are acting out of their roles!");
					System.out.println("Username: "+ user.getFullname());
					System.out.println("Action: " + action);
					resp.sendRedirect(req.getContextPath() + UrlConstants.ERROR);
					//req.getRequestDispatcher("/views/error/403.jsp").forward(req, resp);
					break;
				}
				
				//Nếu là quyền admin là được vào tất cả edit trừ các tên miền của manager sẽ chặn trước ở trên.
				else if(user.getRoleName().equals("ROLE_ADMIN")) {
					System.out.println("Username: "+ user.getFullname());
					System.out.println("Action: " + action);
					chain.doFilter(req, resp);
					break;
				}
				
				//Quyền manager qua được tới đây thì break
				else if(user.getRoleName().equals("ROLE_MANAGER")) {
					System.out.println("Managers are editing stuff!");
					break;
				}
				
				else {
					System.out.println(user.getRoleName()+"s are acting out of their roles!");
					System.out.println("Username: "+ user.getFullname());
					System.out.println("Action: " + action);
					resp.sendRedirect(req.getContextPath() + UrlConstants.ERROR);
					//req.getRequestDispatcher("/views/error/403.jsp").forward(req, resp);
					break;
				}
			}
			default:
				break;
			}
		}
		
		//Url kết thúc bằng details thì Admin và Manager xem được của User chỉ xem được của bản thân thôi
		else if(action.endsWith("/details")) {
			//Kiểm tra quyền
			switch (action) {
			//Xem user của admin và manager khác với user
			case UrlConstants.ADMIN_USER_DETAILS:
			case UrlConstants.MANAGER_USER_DETAILS:
			
			{
				if(user.getRoleName().equals("ROLE_USER") && user.getId() == Integer.parseInt(req.getParameter("id"))) {
					System.out.println("Username: "+ user.getFullname());
					System.out.println("Action: " + action);
					chain.doFilter(req, resp);
					break;					
				}
				else if(user.getRoleName().equals("ROLE_ADMIN") || user.getRoleName().equals("ROLE_MANAGER")) {
					System.out.println("Username: "+ user.getFullname());
					System.out.println("Action: " + action);
					chain.doFilter(req, resp);
					break;					
				}
				else {
					System.out.println(user.getRoleName()+"s are acting out of their roles!");
					System.out.println("Username: "+ user.getFullname());
					System.out.println("Action: " + action);
					resp.sendRedirect(req.getContextPath() + UrlConstants.ERROR);
					//req.getRequestDispatcher("/views/error/403.jsp").forward(req, resp);
					break;
				}
			}
			default:
			{
				break;
			}
			}
		}	

		//Kiểm tra url bắt đầu bằng admin
		//Những controller admin được phép vào.
		else if(action.startsWith("/admin")) {
			//Kiểm tra quyền
			switch(action) {
			
			case UrlConstants.ADMIN_USER_ADD:
			case UrlConstants.ADMIN_USER_DELETE:
			case UrlConstants.ADMIN_USER_EDIT:
			case UrlConstants.ADMIN_ROLE_ADD:
			case UrlConstants.ADMIN_ROLE_DELETE:
			case UrlConstants.ADMIN_ROLE_EDIT:
			{
				if(user.getRoleName().equals("ROLE_ADMIN")) {//Có quyền admin
					System.out.println("Username: "+ user.getFullname());
					System.out.println("Action: " + action);
					chain.doFilter(req, resp);//Cho phép vào controller.
					break;
				}
				else {
					System.out.println(user.getRoleName()+"s are acting out of their roles!");
					System.out.println("Username: "+ user.getFullname());
					System.out.println("Action: " + action);
					resp.sendRedirect(req.getContextPath() + UrlConstants.ERROR);
					//req.getRequestDispatcher("/views/error/403.jsp").forward(req, resp);
					break;
				}
			}
			default:{
				System.out.println("Admin Details");
				break;
			}
			}

		}
		
		else {
			//Có gì hỏi thử cái css! Nếu nhớ.
			resp.sendRedirect(req.getContextPath() + UrlConstants.ERROR);
			//req.getRequestDispatcher("/views/error/403.jsp").forward(req, resp);
		}
	}
}
