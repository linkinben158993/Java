package com.myclass.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity(name = "categories")
public class Categories extends GenericEntities<String> {
	@Id
	private int id;
	@NotBlank(message = "Vui lòng nhập tiêu đề!")
	private String title;
	private String icon;
	private int order_index;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Courses> courses;

	public Categories() {

	}

	public Categories(int id, String title, String icon, int order_index) {
		super();
		this.id = id;
		this.title = title;
		this.icon = icon;
		this.order_index = order_index;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getOrder_index() {
		return order_index;
	}

	public void setOrder_index(int order_index) {
		this.order_index = order_index;
	}

}
