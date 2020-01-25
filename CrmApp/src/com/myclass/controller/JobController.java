package com.myclass.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.constants.UrlConstants;
import com.myclass.dao.JobDAO;
import com.myclass.entity.Job;

//"/user/job","/manager/job","/manager/job/add","/manager/job/edit","/manager/job/details"
@WebServlet(name = "JobController", urlPatterns = {
		UrlConstants.USER_JOB_LIST,UrlConstants.MANAGER_JOB_LIST,UrlConstants.MANAGER_JOB_ADD,
		UrlConstants.MANAGER_JOB_DELETE,UrlConstants.MANAGER_JOB_EDIT,UrlConstants.MANAGER_JOB_DETAILS})
public class JobController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	private JobDAO jobDAO = null;
	
	public JobController() {
		jobDAO = new JobDAO();
		
	}
	
	@Override
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("USER_LOGIN") == null) {
			resp.sendRedirect(req.getContextPath() + "/login?error=3");
			return;
		}
		
		else {
			String action = req.getServletPath();
			
			switch(action) {
			case UrlConstants.USER_JOB_LIST:
			case UrlConstants.MANAGER_JOB_LIST:{
				getList(req, resp);
				break;
			}
			case UrlConstants.MANAGER_JOB_ADD:{
				getAdd(req,resp);
				break;
			}
			case UrlConstants.MANAGER_JOB_EDIT:{
				getEdit(req, resp);
				break;
			}
			case UrlConstants.MANAGER_JOB_DELETE:{
				getDelete(req, resp);
				break;
			}
			case UrlConstants.MANAGER_JOB_DETAILS:{
				req.setAttribute("message", "Undone Features! Please return later! Redirect to Job List!");
				//getDetail(req, resp);
				req.setAttribute("jobs", jobDAO.findAll());
				req.getRequestDispatcher("/views/job/index.jsp").forward(req, resp);
				break;
			}
			
			}
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch(action) {
		case UrlConstants.MANAGER_JOB_ADD:{
			try {
				postAdd(req, resp);
				break;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		case UrlConstants.MANAGER_JOB_EDIT:{
			try {
				postEdit(req, resp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		}
	}
	
	private void getList(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		List<Job> jobs = jobDAO.findAll();
		req.setAttribute("jobs", jobs);
		req.getRequestDispatcher("/views/job/index.jsp").forward(req, resp);
	}
	
	private void getAdd(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.getRequestDispatcher("/views/job/add.jsp").forward(req, resp);
	}
	
	
	private void getEdit(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		int jobId = Integer.parseInt(req.getParameter("id"));
		req.setAttribute("id", jobId);
		req.setAttribute("job", jobDAO.findById(jobId));
		req.getRequestDispatcher("/views/job/edit.jsp").forward(req, resp);
	}
	
	/*
	 * private void getDetail(HttpServletRequest req, HttpServletResponse resp)
	 * throws ServletException, IOException{ //req.setCharacterEncoding("UTF-8");
	 * req.setAttribute("tasks", jobDAO.findAllTask());
	 * req.getRequestDispatcher("/views/job/details.jsp").forward(req, resp); }
	 */
	
	private void getDelete(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException {
		int jobId = Integer.parseInt(req.getParameter("id"));
		jobDAO.delete(jobId);
		resp.sendRedirect(req.getContextPath() + UrlConstants.USER_JOB_LIST);
	}
	
	private void postAdd(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException, ParseException {
		req.setCharacterEncoding("UTF-8");
		
		String name = req.getParameter("name");

		//Lấy ngày tháng trên fomr
		String start_date_form = req.getParameter("start_date");
		String end_date_form = req.getParameter("end_date");
		
		//Test lấy định dạng
		System.out.println(start_date_form);
		System.out.println(end_date_form);
		
		//Parse ngày tháng thành String để ép kiểu về sql hình như hơi dư.
		SimpleDateFormat start_date_trans = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat end_date_trans = new SimpleDateFormat("yyyy-MM-dd");
		Date start_date = new Date(start_date_trans.parse(start_date_form).getTime());
		Date end_date = new Date (end_date_trans.parse(end_date_form).getTime());
		
		Job job = new Job(name, start_date, end_date);
		
		int result = jobDAO.add(job);
		
		if(result < 1) {
			req.setAttribute("message", UrlConstants.ADD_FAILED);
			req.getRequestDispatcher("/views/job/add.jsp").forward(req, resp);
			return;
		}
		
		else {
			req.setAttribute("message", UrlConstants.ADD_SUCCESS);
			req.setAttribute("jobs", jobDAO.findAll());
			req.getRequestDispatcher("/views/job/index.jsp").forward(req, resp);
			return;
		}
	}
	private void postEdit(HttpServletRequest req, HttpServletResponse resp) 
			throws ParseException, ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String name = req.getParameter("name");
		
		int jobId = Integer.parseInt(req.getParameter("id"));

		//Lấy ngày tháng trên fomr
		String start_date_form = req.getParameter("start_date");
		String end_date_form = req.getParameter("end_date");
		
		//Parse ngày tháng thành String để ép kiểu về sql hình như hơi dư.
		SimpleDateFormat start_date_trans = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat end_date_trans = new SimpleDateFormat("yyyy-MM-dd");
		Date start_date = new Date(start_date_trans.parse(start_date_form).getTime());
		Date end_date = new Date (end_date_trans.parse(end_date_form).getTime());
		
		Job job = new Job(name, start_date, end_date);
		
		job.setId(jobId);
		
		int result = jobDAO.update(job);
		
		if(result < 1) {
			req.setAttribute("message", UrlConstants.EDIT_FAILED);
			req.getRequestDispatcher("/views/job/edit.jsp").forward(req, resp);
			return;
		}
		
		else {
			req.setAttribute("message", UrlConstants.EDIT_SUCCESS);
			req.setAttribute("jobs", jobDAO.findAll());
			req.getRequestDispatcher("/views/job/index.jsp").forward(req, resp);
			return;
		}
	}
}
