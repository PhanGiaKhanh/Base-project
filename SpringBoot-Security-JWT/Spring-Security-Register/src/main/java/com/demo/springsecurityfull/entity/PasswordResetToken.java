package com.demo.springsecurityfull.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @author: KhanhPG
 * @since: 28/02/2022
 */
@Entity
public class PasswordResetToken {
	// Epiration time 10 minutes
	private static final int EXPIRATION_TIME = 10;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String token;
	private Date expirationTime;

	@OneToOne(fetch = FetchType.EAGER) // khi select lấy ra nhiều nhất 1 object với @ManyToOne, và @OneToOne
	@JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_PASSWORD_TOKEN"))
	private User user;

	public PasswordResetToken() {
	}

	public PasswordResetToken(String token, User user) {
		this.token = token;
		this.user = user;
		this.expirationTime = calculateExpiratoinDate(EXPIRATION_TIME);
	}

	public PasswordResetToken(String token) {
		this.token = token;
		this.expirationTime = calculateExpiratoinDate(EXPIRATION_TIME);
	}

	private Date calculateExpiratoinDate(int expirationTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(Calendar.MINUTE, expirationTime);
		return new Date(calendar.getTime().getTime());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
