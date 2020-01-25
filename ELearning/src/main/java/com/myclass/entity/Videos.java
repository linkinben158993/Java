package com.myclass.entity;

public class Videos {
	private int id;
	private String title;
	private String url;
	private int time_count;
	private int order_index;
	private int course_id;
	private String image;
	
	public Videos() {
		
	}
	public Videos(int id, String title, String url, int time_count, int order_index, int course_id, String image) {
		super();
		this.id = id;
		this.title = title;
		this.url = url;
		this.time_count = time_count;
		this.order_index = order_index;
		this.course_id = course_id;
		this.image = image;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getTime_count() {
		return time_count;
	}
	public void setTime_count(int time_count) {
		this.time_count = time_count;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
