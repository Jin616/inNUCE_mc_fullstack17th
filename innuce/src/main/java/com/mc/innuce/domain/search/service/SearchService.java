package com.mc.innuce.domain.search.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.search.dao.SearchDAO;
import com.mc.innuce.domain.search.dto.KeysOfSearchDTO;
import com.mc.innuce.domain.search.dto.SearchDTO;

@Service
public class SearchService {
	@Autowired
	SearchDAO dao;

	public List<String> getNewsContent() {
		return dao.getNewsContent();
	}

	public int updateSearch(int keywordKey) {
		return dao.updateSearch(keywordKey);
	}

	public int insertSearch(SearchDTO sDTO) {
		return dao.insertSearch(sDTO);
	}




}
