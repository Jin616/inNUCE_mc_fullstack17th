package com.mc.innuce.domain.search.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mc.innuce.domain.search.dto.BoardDTO;

@Repository
@Mapper
public interface BoardDAO {

	public List<BoardDTO> boardList(int[] limit);

	public int getTotalBoard();

	public int insertBoard(BoardDTO dto);

	public void updateViewCount(int seq);

	public BoardDTO getDetail(int seq);

}
