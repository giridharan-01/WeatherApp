package com.proj.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.codec.digest.DigestUtils;



@Entity
@Table(name ="projuser")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int id;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = DigestUtils.md5Hex(password);
	}
	@Column(name = "name")
	private String name;
	
	@Column(name="username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	public Users() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + "]";
	}
}
