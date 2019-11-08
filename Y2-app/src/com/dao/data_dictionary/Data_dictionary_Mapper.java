package com.dao.data_dictionary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.entity.Data_dictionary;

public interface Data_dictionary_Mapper {
	/**
	 * 查询
	 * @param typeCode
	 * @return
	 */
	public List<Data_dictionary> getdictionarylist(@Param("typeCode")String typeCode);
}
