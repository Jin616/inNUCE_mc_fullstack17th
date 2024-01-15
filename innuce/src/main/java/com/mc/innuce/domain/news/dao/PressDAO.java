package com.mc.innuce.domain.news.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mc.innuce.domain.news.dto.PressDTO;

@Repository
@Mapper
public interface PressDAO {

	public List<PressDTO> selectAllPress();

	public int insertListPress(List<PressDTO> insertList);

	public int insertPress(PressDTO dto);
	
	public int updatePress(PressDTO dto);
	
}