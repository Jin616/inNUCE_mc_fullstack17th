package com.mc.innuce.domain.search.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mc.innuce.domain.search.dto.KeysOfSearchDTO;
import com.mc.innuce.domain.search.dto.SearchDTO;

@Repository
@Mapper
public interface SearchDAO {

	List<String> getNewsContent();

	List<Integer> selectPassedKeywordList(int createRoomCount);

	int updateSearch(int keywordKey);

	int insertSearch(SearchDTO sDTO);


	
}
