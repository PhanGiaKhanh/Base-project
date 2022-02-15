package com.example.springboot.models;

import java.util.Calendar;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "DB_Product")
public class Product {
	@Id
	//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//  You can also use "sequence" > custom auto increment
	@SequenceGenerator(
			name = "product_sequence",
			sequenceName = "product_sequence",
			allocationSize =  1 // increment by 1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "product_sequence"
			)
	private Long id;
	@Column(nullable = false, unique = true, length = 200)
	private String productName;
	private int year;
	private Double price;
	private String url;
	
	// default contructor
	public Product() {
	}
	
	// calculated field = transient, not exists in MySql
	@Transient
	private int age;
	
	public int getAge() {
		return Calendar.getInstance().get(Calendar.YEAR) - year;
	}
	
	public Product(String productName, int year, Double price, String url) {
		this.productName = productName;
		this.year = year;
		this.price = price;
		this.url = url;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", year=" + year + ", price=" + price + ", url="
				+ url + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Product product = (Product) obj;
		return year == product.year 
				&& age == product.age 
				&& Objects.equals(id, product.id) 
				&& Objects.equals(productName, product.productName)
				&& Objects.equals(price, product.price)
				&& Objects.equals(url, product.url);
	}
}
