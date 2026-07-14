package com.amzon.service;

import com.amzon.AmazonOrderApplication;
import com.amzon.controller.UserController;
import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amzon.entity.UserEntity;
import com.amzon.repository.UserRepository;
import com.amzon.request.UserRequest;

@Service
public class UserService 
{
	@Autowired
	UserRepository userRepository;

	// Saving Otp in database
	
	public String saveOtp(String mobile, String name)
	{
		UserEntity userEntity = userRepository.findByMobile(mobile);

		if (userEntity == null) 
		{
			userEntity = new UserEntity();
			userEntity.setMobile(mobile);
			userEntity.setName(name);
		}

		String otp = generateOtp();

		userEntity.setOtp(otp);
		userEntity.setStatus("PENDING");

		UserEntity response = userRepository.save(userEntity);

		if (response.getUserId() > 0) 
		{
			System.out.println("User created successfully" + response.getUserId());
		} 
		
		else
		{
			System.out.println("Unable to create User");
		}

		return response.getOtp();
	}

	// Generating random Otp
	
	public String generateOtp() 
	{
		SecureRandom random = new SecureRandom();
		int otp = 100000 + random.nextInt(900000);

		return String.valueOf(otp);
	}

	// Verifying Otp with mobile and Otp

	public boolean verifyOtp(String mobile, String otp)
	{
		UserEntity entity = userRepository.findByMobileAndOtp(mobile, otp);

		// if(entity.getMobile().equals(mobile) && entity.getOtp() == otp)

		if (entity == null)
		{
			return false;
		}

		entity.setStatus("Verified");
		userRepository.save(entity);
		System.out.println("Your database mobile " + entity.getMobile());
		System.out.println("Your respose mobile : " + mobile);

		System.out.println("Your database Otp : " + entity.getOtp());
		System.out.println("Your response Otp : " + otp);

		return true;

	}
	
	// Get all users
	
	public List<UserEntity> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	//Get User by Id
	
	public UserEntity getUserById(int userId)
	{
		return userRepository.findById(userId).orElse(null);
	}
	
	//Updating the User
	
	public UserEntity updateUser(int userId,UserRequest userRequest)
	{
		UserEntity user=userRepository.findById(userId).orElse(null);
		
		if(user==null)
		{
			return null;
		}
		
		user.setMobile(userRequest.getMobile());
		user.setName(userRequest.getName());
		
		return userRepository.save(user);
	}
	
	// Deleting the user
	
	public String deleteUser(int userId)
	{
		userRepository.deleteById(userId);
		
		return "User deleted successfully";
	}
}
