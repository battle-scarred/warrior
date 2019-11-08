package com.service.data_dictionary;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dao.data_dictionary.Data_dictionary_Mapper;
import com.entity.Data_dictionary;
@Service
public class Data_dictionary_Service_Impl implements Data_dictionary_Service{
	@Resource
	private Data_dictionary_Mapper dicionMapper;
	
	@Override
	public List<Data_dictionary> getdictionarylist(String typeCode) {
		List<Data_dictionary> list = null;
		try {
			list = dicionMapper.getdictionarylist(typeCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

}
