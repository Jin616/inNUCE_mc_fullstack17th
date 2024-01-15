package com.mc.innuce.domain.news.dao;

import java.sql.Timestamp;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author JIN
 *
 */
@Repository
@Mapper
public interface DBUpdateTimeDAO {

	Timestamp selectPressUpdateTime();

	void updatePressUpdateTime();
	
	
	
}
