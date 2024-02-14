package com.mc.innuce.domain.debate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public ModelAndView debateMain(@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		ModelAndView mv = new ModelAndView();
		
		// debate_room_status 가 2 또는 1인 목록
		List<DebateRoomDTO> openDebateRoomList = debateRoomService.openDebateRoomList((page - 1) * 10);
		int totalCount = debateRoomService.openDebateRoomCount();
		
		mv.addObject("page", page);
		mv.addObject("pageCount", 10);
		mv.addObject("totalCount", totalCount);
		
		if(openDebateRoomList == null || openDebateRoomList.isEmpty()) {
			mv.setViewName("debate/debate");
			return mv;
		}
		
		// 위의 목록의 debate_room_key 목록 생성
		List<Integer> openDebateRoomKeyList = new ArrayList<>();
		for(int i = 0; i < openDebateRoomList.size(); i++) {
			openDebateRoomKeyList.add(openDebateRoomList.get(i).getDebate_room_key());
		}
		
		// debate_room_key 목록으로 해당하는 방의 실시간 참여자 수 목록 반환
		List<Integer> openDebateRoomUserConnectCountList = debateUserService.openDebateRoomUserConnectCountList(openDebateRoomKeyList);
		// debate_room_key 목록으로 해당하는 방의 전체 참여자 수 목록 반환
		List<Integer> openDebateRoomUserCountList = debateUserService.openDebateRoomUserCountList(openDebateRoomKeyList);
		
		mv.addObject("openDebateRoomList", openDebateRoomList);
		mv.addObject("openDebateRoomUserConnectCountList", openDebateRoomUserConnectCountList);
		mv.addObject("openDebateRoomUserCountList", openDebateRoomUserCountList);
		mv.setViewName("debate/debate");

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
		if(udto == null) {
			System.out.println("로그인 안됨");
			mv.setViewName("redirect:/debate");
			return mv;
		}

		// DebateUserDTO
		DebateUserDTO dudto = new DebateUserDTO();
		dudto.setDebate_room_key(roomId);
		dudto.setUser_key(udto.getUser_key());

		// roomId 유효성 검사, DebateUser 테이블 중복 확인 후 결과 반환
		// 기존에 들어가 있었으면 닫혀 있어도 0 아님
		int dukey = debateUserService.createDebateUser(dudto);

		// 결과값이 0 이면 존재하지 않거나 닫혀있는 방번호 -> 토론방 목록 메인 화면으로
		if (dukey == 0) {
			mv.setViewName("redirect:/debate");
			return mv;
		}

		// 결과값이 0이 아니면 새로 생성 되었거나 존재했던 DebateUser의 key값을 DebateUserDTO에 저장
		dudto.setDebate_user_key(dukey);

		// 후 ModelAndView로 전달
		mv.addObject("debateuser", dudto);

		// debate_room dto 반환
		DebateRoomDTO drdto = debateRoomService.selectOneDebateRoom(roomId);

		// debate_room dto 전달
		mv.addObject("debateroom", drdto);

		mv.setViewName("debate/debateroom");

		return mv;
	}
	
	// 채팅바 채팅방 리스트
	@PostMapping("/chattingroomlist")
	@ResponseBody
	public List<DebateRoomDTO> chattingRoomList(){
		return debateRoomService.chattingRoomList();
	}

}
