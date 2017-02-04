package com.softtek.academy.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtek.academy.domain.CartEntity;
import com.softtek.academy.domain.UserEntity;
import com.softtek.academy.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartService cartService;
	
	@Override
	public List<UserEntity> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
	@Override
	public List<String> listStatus(){
		List<String>status=new ArrayList<String>();
		status.add("S");
		status.add("N");
		return status;
		
	}
	@Override
	public UserEntity findOne(String username) {
		// TODO Auto-generated method stub
		UserEntity user;
		try{
			 user=userRepository.findOne(username);
		}catch(EntityNotFoundException ex){
			return null;
		}
		
		return user;
	}

	@Override
	public boolean save(UserEntity user) {
		// TODO Auto-generated method stub
		final CartEntity  cart=new CartEntity();
		if(isValid(user)){
			System.out.println("entro1");
			if(userRepository.findOne(user.getUsername())==null){
				System.out.println("entro2");
				cart.setCartAmount(0.0);
				cart.setShippingAmount(0.0);
				cart.setUser(user);
				cart.setStaus(true);
				cart.setCreateDate(new java.sql.Date(new java.util.Date().getTime()));
				cart.setUpdateDate(new java.sql.Date(new java.util.Date().getTime()));
				System.out.println(cart);
				userRepository.save(user);
				cartService.save(cart);
			}else{
				System.out.println("entro3");
				userRepository.save(user);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean isValid(UserEntity user) {
		// TODO Auto-generated method stub
		if(user.getUsername()==null || user.getUsername().isEmpty()){
			
			return false;
		}
		if(user.getName()==null||user.getName().isEmpty()){
			
			return false;
		}
		if(user.getPassword()==null||user.getPassword().isEmpty()){
			
			return false;
		}
		if(user.getStatus()==null){
			
			return false;
		}
		if(user.getRole()==null){
			if(user.getRole().getDescription()==null||user.getRole().getDescription().isEmpty()){
				
				return false;
			}
			if(user.getRole().getId()==null||user.getRole().getId().isEmpty()){
				
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean delete(UserEntity user) {
		// TODO Auto-generated method stub
		try{
			userRepository.delete(user);
		}catch(Exception ex){
			return false;
		}
		return true;
	}

}
