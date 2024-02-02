package com.mc.innuce.domain.debate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mc.innuce.domain.debate.dto.OpinionDTO;

@Repository
@Mapper
public interface OpinionDAO {

	int insertOpinion(OpinionDTO opinion);

	OpinionDTO selectOneOpinion(int opinion_key);

	int selectLastInsertId();

	int selectLastOpinion(int roomId);

	List<OpinionDTO> selectPreOpinionList(int opinion_key);

	List<OpinionDTO> selectOnePreOpinionList(int opinion_key);

}
