package com.amzon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amzon.entity.UserEntity;
import com.amzon.request.UserRequest;
import com.amzon.request.VerifyUser;
import com.amzon.service.UserService;

@RestController
@RequestMapping("/otp")
public class UserController
{
	@Autowired
	UserService userService;
	
	// Generate

	@PostMapping("/generate")
	public String generateOtp(@RequestBody UserRequest userRequest)
	{
		String mobile=userRequest.getMobile();
		String name=userRequest.getName();
		
		String responseOtp=userService.saveOtp(mobile, name);
		
		return "Your one time Otp is : "+responseOtp;
	}
	
	//Verify User
	
	@PostMapping("/verify")
	public String verifyOtp(@RequestBody VerifyUser verifyUser)
	{
		 boolean response=userService.verifyOtp(verifyUser.getMobile(), verifyUser.getOtp());
		 
		 if(response)
		 {
			 return "Otp verified successfully";
		 }
		 
		 return "Invalid Otp";
	}
	
	// Get all users
	
	@PostMapping("/users")
	public List<UserEntity> getAllUser()
	{
		return userService.getAllUsers();
	}
	
	//Get user by ID
	
	@GetMapping("/users/{userId}")
	public UserEntity getUserById(@PathVariable int userId)
	{
		return userService.getUserById(userId);
	}
	
	//Update User
	
	@PutMapping("/users/{userId}")
	public UserEntity updateUser(@PathVariable int userId,@RequestBody UserRequest userRequest)
	{
		return userService.updateUser(userId, userRequest);
	}
	
	//Delete User
	
	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable int userId)
	{
		return userService.deleteUser(userId);
	}
	
}
