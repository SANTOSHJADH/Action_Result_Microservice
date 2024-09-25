package com.collection.ActionResult.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collection.ActionResult.Entity.UserEntity;
import com.collection.ActionResult.Model.UserModel;
import com.collection.ActionResult.ServiceImpl.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServiceImpl userservice;

	@PostMapping(path = "/register")
	public UserEntity register(@Valid @RequestBody UserModel user) {
		UserEntity entity = new UserEntity(user);
		return userservice.addUser(entity);
	}

	@GetMapping(path = "/test")
	public ResponseEntity<String> getData() {
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}

	@PostMapping("/login")
	public String login(@RequestBody UserEntity user) {

		return userservice.verify(user);
	}

}
