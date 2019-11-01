package com.service.dev_user;

import org.apache.ibatis.annotations.Param;

import com.entity.Dev_user;

public interface Dev_user_Service {
	/**
	 * 使用用户名查询是否存在
	 * @param devCode		编号
	 * @return				返回信息
	 */
	public Dev_user login(@Param("devCode")String devCode);
}
