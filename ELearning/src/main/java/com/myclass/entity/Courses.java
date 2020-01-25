package com.myclass.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity(name = "courses")
public class Courses {
	@Id
	private int id;
	@NotBlank(message = "Vui lòng nhập tiêu đề khóa học!")
	private String title;
	@NotBlank(message = "Vui lòng chọn hình ảnh cho khóa học!")
	private String image;
	@NotBlank(message = "Vui lòng nhập số tiết!")
	private int lectures_count;
	@NotBlank(message = "Vui lòng nhập số giờ học!")
	private int hours_count;
	private int view_count;
	@NotBlank(message = "Vui lòng nhập giá của khóa học!")
	private double price;
	private int discount;
	private double promotion_price;
	@NotBlank(message = "Vui lòng nhập miêu tả khóa học!")
	private String description;
	private String content;
	
	@Column(name = "category_id")
	@NotBlank(message = "Vui lòng nhập tiêu đề khóa học!")
	private int category_id;
	
	private Date last_update;
	
	@ManyToOne()
	@JoinColumn(name = "category_id", insertable = false, updatable = false, nullable = true)
	private Categories category;
	
	public Courses() {
		
	}
	
	public Courses(int id, String title, String image, int lectures_count, int hours_count, int view_count,
			double price, int discount, double promotion_price, String description, String content, int category_id,
			Date last_update) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.lectures_count = lectures_count;
		this.hours_count = hours_count;
		this.view_count = view_count;
		this.price = price;
		this.discount = discount;
		this.promotion_price = promotion_price;
		this.description = description;
		this.content = content;
		this.category_id = category_id;
		this.last_update = last_update;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getLectures_count() {
		return lectures_count;
	}
	public void setLectures_count(int lectures_count) {
		this.lectures_count = lectures_count;
	}
	public int getHours_count() {
		return hours_count;
	}
	public void setHours_count(int hours_count) {
		this.hours_count = hours_count;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public double getPromotion_price() {
		return promotion_price;
	}
	public void setPromotion_price(double promotion_price) {
		this.promotion_price = promotion_price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public Date getLast_update() {
		return last_update;
	}
	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
}
