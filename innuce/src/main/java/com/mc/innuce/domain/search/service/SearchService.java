package com.mc.innuce.domain.search.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.search.dao.SearchDAO;
import com.mc.innuce.domain.search.dto.SearchDTO;

@Service
public class SearchService {
	@Autowired
	SearchDAO dao;

	public List<String> getNewsContent() {
		return dao.getNewsContent();
	}

	public int updateSearch(SearchDTO sDTO) {
		return dao.updateSearch(sDTO);
	}

	public int updateSearch2(SearchDTO sDTO) {
		return dao.updateSearch2(sDTO);
	}

	public int insertSearch(SearchDTO sDTO) {
		return dao.insertSearch(sDTO);
	}

	public int insertSearch2(SearchDTO sDTO) {
		return dao.insertSearch2(sDTO);
	}

	public SearchDTO oneSearch(SearchDTO sDTO) {
		return dao.oneSearch(sDTO);
	}

	public SearchDTO oneSearch2(SearchDTO sDTO) {
		return dao.oneSearch2(sDTO);
	}
	
}
