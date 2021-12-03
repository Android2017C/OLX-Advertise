package com.olx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Advertisement Model")
public class Advertisements {
	@ApiModelProperty("Unique identifier of the Advertisement")
	private int id;
	@ApiModelProperty("Title of Advertisement")
	private String title;
	@ApiModelProperty("Price of Advertisement")
	private double price;
	@ApiModelProperty("Category Id of the Advertisement")
	private int categoryId;
	@ApiModelProperty("Description About Advertisement")
	private String description;

	private String status;
	private String created_date;
	private String modified_date;
	private String active;
	private String username;

	public Advertisements() {

	}

	public Advertisements(String title, double price, int categoryId, String description, String status,
			String created_date, String modified_date, String active, String username) {
		super();
		this.title = title;
		this.price = price;
		this.categoryId = categoryId;
		this.description = description;
		this.status = status;
		this.created_date = created_date;
		this.modified_date = modified_date;
		this.active = active;
		this.username = username;
	}

	public Advertisements(int id, String title, double price, int categoryId, String description, String status,
			String created_date, String modified_date, String active, String username) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.categoryId = categoryId;
		this.description = description;
		this.status = status;
		this.created_date = created_date;
		this.modified_date = modified_date;
		this.active = active;
		this.username = username;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getModified_date() {
		return modified_date;
	}

	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Advertisements [id=" + id + ", title=" + title + ", price=" + price + ", categoryId=" + categoryId
				+ ", description=" + description + ", status=" + status + ", created_date=" + created_date
				+ ", modified_date=" + modified_date + ", active=" + active + ", username=" + username + "]";
	}

}
