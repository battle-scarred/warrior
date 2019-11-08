package com.service.backend_user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.backend_user.Backend_user_Mapper;
import com.entity.Backend_user;
@Service
public class Backend_user_Service_Impl implements Backend_user_Service{

	@Resource
	private Backend_user_Mapper userMapper;
	@Override
	public Backend_user login(String devCode) {
		Backend_user user = null;
		try {
			user =userMapper.login(devCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
