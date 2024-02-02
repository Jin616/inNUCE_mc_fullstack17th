package com.mc.innuce.domain.search.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SearchDAO {

	List<Integer> selectPassedKeywordList(int createRoomCount);
	
}
