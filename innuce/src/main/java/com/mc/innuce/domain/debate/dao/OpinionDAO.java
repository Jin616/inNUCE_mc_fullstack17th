package com.mc.innuce.domain.debate.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mc.innuce.domain.debate.dto.OpinionDTO;

@Repository
@Mapper
public interface OpinionDAO {

	int insertOpinion(OpinionDTO opinion);

	OpinionDTO selectOneOpinion(int opinion_key);

	int selectLastInsertId();

}
