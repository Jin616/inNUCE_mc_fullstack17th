package com.mc.innuce.domain.debate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.debate.dao.OpinionDAO;
import com.mc.innuce.domain.debate.dto.OpinionDTO;

@Service
public class OpinionService {
	@Autowired
	OpinionDAO opinionDAO;

	public int insertOpinion(OpinionDTO opinion) {
		// TODO Auto-generated method stub
		opinionDAO.insertOpinion(opinion);
		return opinionDAO.selectLastInsertId();
	}

	public OpinionDTO selectOneOpinion(int opinion_key) {
		// TODO Auto-generated method stub
		return opinionDAO.selectOneOpinion(opinion_key);
	}

	public int selectLastOpinion(int roomId) {
		// TODO Auto-generated method stub
		return opinionDAO.selectLastOpinion(roomId);
	}

	public List<OpinionDTO> selectPreOpinionList(int opinion_key) {
		// TODO Auto-generated method stub
		return opinionDAO.selectPreOpinionList(opinion_key);
	}

	public List<OpinionDTO> selectOnePreOpinionList(int opinion_key) {
		// TODO Auto-generated method stub
		return opinionDAO.selectOnePreOpinionList(opinion_key);
	}

}
