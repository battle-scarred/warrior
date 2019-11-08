package com.service.backend_user;


import com.entity.Backend_user;

public interface Backend_user_Service {
	/**
	 * 按用户号查询
	 * @param devCode
	 * @return
	 */
	public Backend_user login(String devCode);
	
}
