package com.mc.innuce.domain.search.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.mc.innuce.domain.search.dto.MemberDTO;
@Mapper
@Repository
public interface MemberDAO {

	public MemberDTO oneMember(String id);

	public int insertMember(MemberDTO newDTO);

}
