package com.amzon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amzon.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>
{
	public UserEntity findByMobile(String mobile);
	
	public UserEntity findByMobileAndOtp(String mobile,String otp);
}
