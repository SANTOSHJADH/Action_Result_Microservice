package com.collection.ActionResult.Entity;

import com.collection.ActionResult.Model.UserModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_entity")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer Id;
	public String username;
	public String password;

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserEntity(UserModel userDto) {
		this.username = userDto.getUsername();
		this.password = userDto.getPassword();
	}

	public UserEntity(Integer id, String username, String password) {
		super();
		Id = id;
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
