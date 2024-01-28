package com.mc.innuce.domain.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.search.dao.MemberDAO;
import com.mc.innuce.domain.search.dto.MemberDTO;
@Service
public class MemberService {
	
	@Autowired
	MemberDAO dao;
	

	public MemberDTO oneMember(String id) {
		// TODO Auto-generated method stub
		return dao.oneMember(id);
	}


	public void registerMember(MemberDTO newDTO) {
		int result=dao.insertMember(newDTO);
		System.out.println(result);
	}

}
