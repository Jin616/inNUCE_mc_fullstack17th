package com.mc.innuce.domain.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.search.dao.BoardDAO;
import com.mc.innuce.domain.search.dto.BoardDTO;

@Service
public class BoardService {

	@Autowired
	BoardDAO boardDAO;
	
	public List<BoardDTO> boardList(int[] limit) {
		// TODO Auto-generated method stub
		return boardDAO.boardList(limit);
	}

	public int getTotalBoard() {
		// TODO Auto-generated method stub
		return boardDAO.getTotalBoard();
	}

	public void registerBoard(BoardDTO dto) {
		int result=boardDAO.insertBoard(dto);
		System.out.println(result);
	}

	public BoardDTO updateViewCountAndGetDetail(int seq) {
		boardDAO.updateViewCount(seq);
		return boardDAO.getDetail(seq);
	}
	
}
