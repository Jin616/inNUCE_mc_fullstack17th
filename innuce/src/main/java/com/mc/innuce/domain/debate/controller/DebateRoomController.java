package com.mc.innuce.domain.debate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mc.innuce.domain.debate.dto.DebateRoomDTO;
import com.mc.innuce.domain.debate.dto.DebateUserDTO;
import com.mc.innuce.domain.debate.service.DebateRoomService;
import com.mc.innuce.domain.debate.service.DebateUserService;
import com.mc.innuce.domain.user.dto.UserDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class DebateRoomController {
	@Autowired
	private DebateRoomService debateRoomService;
	
	@Autowired
	private DebateUserService debateUserService;
	
	// 토론방 목록 메인 화면
	@GetMapping("/debate")
	public ModelAndView debateMain() {
		// debate_room_status 가 1인 목록
		List<DebateRoomDTO> openDebateRoomList = debateRoomService.openDebateRoomList();
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("openDebateRoomList", openDebateRoomList);
		mv.setViewName("debate/debatemain");
		
		return mv;
	}
	
	// 선택한 토론방 화면
	@GetMapping("/debate/{roomId}")
	public ModelAndView debateRoom(@PathVariable int roomId, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		// login user
		UserDTO udto = (UserDTO) session.getAttribute("login_user");
		
		// 로그인이 되어 있지 않다면 -> 토론방 목록 메인 화면으로
		// 테스트 후 복구
//		if(udto == null) {
//			System.out.println("로그인 안됨");
//			mv.setViewName("redirect:/debate");
//			return mv;
//		}
		
		// 테스트 위한 임시 user_key set
		// 테스트 후 삭제
		udto = new UserDTO();
		udto.setUser_key(1);
		
		// DebateUserDTO
		DebateUserDTO dudto = new DebateUserDTO();
		dudto.setDebate_room_key(roomId);
		dudto.setUser_key(udto.getUser_key());
		
		// roomId 유효성 검사, DebateUser 테이블 중복 확인 후 결과 반환
		int dukey = debateUserService.createDebateUser(dudto);
		
		
		// 결과값이 0 이면 존재하지 않거나 닫혀있는 방번호 -> 토론방 목록 메인 화면으로
		if(dukey == 0) {
			mv.setViewName("redirect:/debate");
			return mv;
		}
		
		// 결과값이 0이 아니면 새로 생성 되었거나 존재했던 DebateUser의 key값을 DebateUserDTO에 저장
		dudto.setDebate_user_key(dukey);
		
		// 후 ModelAndView로 전달
		mv.addObject("debateuser", dudto);
		mv.setViewName("debate/debateroom");
		
		return mv;
	}
	
}
