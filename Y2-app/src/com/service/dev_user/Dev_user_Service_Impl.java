package com.service.dev_user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.dev_user.Dev_user_Mapper;
import com.entity.Dev_user;
@Service("userService")
public class Dev_user_Service_Impl implements Dev_user_Service{

	@Resource
	private Dev_user_Mapper userMapper;
	
	@Override
	public Dev_user login(String devCode) {
		Dev_user user=null;
		try {
			user=userMapper.login(devCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
