package com.springmvc.demo.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "products")
public class Product {
	@Id
	private String productId;
	private String categoryId;
    @NotNull
    @NotBlank(message = "Product's name cannot be null")
    @Size(min = 3, max = 300)
	private String productName;
	@Min(0)
	private int price;
    @NotNull
    @NotBlank(message = "Product's description cannot be null")
    @Size(min = 5, max = 1000)
	private String description;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFormatPrice() {
		return String.format("$ %.2f", price * 1.00);
	}
}
