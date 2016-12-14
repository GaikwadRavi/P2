package com.niit.collaborationbackend.model;

import javax.persistence.Entity;


import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "user")
@Component
public class User extends BaseDomain {
	@Id
	private String id;
	private String name;
	private String email;
	private String address;
	private String mobile;
	private String role;
	private String password;
	private boolean isOnLine;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isOnLine() {
		return isOnLine;
	}
	public void setOnLine(boolean isOnLine) {
		this.isOnLine = isOnLine;
	}
	

}
