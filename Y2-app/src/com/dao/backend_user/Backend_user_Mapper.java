package com.dao.backend_user;

import org.apache.ibatis.annotations.Param;

import com.entity.Backend_user;

public interface Backend_user_Mapper {
	/**
	 * 按用户号查询
	 * @param devCode
	 * @return
	 */
	public Backend_user login(@Param("userCode")String userCode);
}
