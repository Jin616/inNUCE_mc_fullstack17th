package com.mc.innuce.domain.debate.service;

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
	
}
