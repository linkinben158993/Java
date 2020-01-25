package com.myclass.entity;

public class Results {
	private int id;
	private String title;
	private int order_index;
	private int course_id;
	
	public Results() {
		
	}
	public Results(int id, String title, int order_index, int course_id) {
		super();
		this.id = id;
		this.title = title;
		this.order_index = order_index;
		this.course_id = course_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getOrder_index() {
		return order_index;
	}
	public void setOrder_index(int order_index) {
		this.order_index = order_index;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
}
