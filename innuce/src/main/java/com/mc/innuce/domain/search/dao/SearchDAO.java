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

	int updateSearch(SearchDTO sDTO);

	int updateSearch2(SearchDTO sDTO);

	int insertSearch(SearchDTO sDTO);

	int insertSearch2(SearchDTO sDTO);

	SearchDTO oneSearch(SearchDTO sDTO);

	SearchDTO oneSearch2(SearchDTO sDTO);

}
