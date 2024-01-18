package com.mc.innuce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mc.innuce.domain.user.dto.UserDTO;
import com.mc.innuce.domain.user.service.UserService;


import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	// 로그인 페이지 (김)
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	//로그인 결과 (김)
	@PostMapping("/loginresult")
	@ResponseBody
	public String loginresult(String user_id,String user_pw, HttpSession session) {
		UserDTO dto = service.selectOneUser(user_id);
		System.out.println(user_id);
		String login_result = null;
		String json_result =null;
		// 해당 아이디를 가진 회원이 user 테이블에 없을 경우
		if (dto == null) {
			login_result = "해당 아이디를 가진 회원이 없습니다.";
			json_result = "{\"login_result\": \"" + login_result + "\" }";
			return json_result;
		}
		
		// 해당 아이디를 가진 회원이 user 테이블에 있는 경우
		else {
			// 올바른 비밀번호 입력
			if(dto.getUser_pw().equals(user_pw)) {
				// 앞으로 세션에 login_user라는 이름으로 userdto 담기
				session.setAttribute("login_user", dto);
				login_result = "로그인 성공.";
				json_result = "{\"login_result\": \"" + login_result + "\" }";
				return json_result;
			}
			// 다른 비밀번호 입력
			else {
				login_result = "잘못된 비밀번호를 입력하셨습니다.";
				json_result = "{\"login_result\": \"" + login_result + "\" }";
				return json_result;
			}
		}
	}
	
	// 회원가입 화면 (김)
	@RequestMapping("/registermember")
	public String registermember(){
		return "registermember";
	}
	
	// 회원가입 처리 (김)
	@PostMapping("/registerresult")
	public String registerProcess(String user_id,String user_pw, String email, String user_name, String phone,
			String birthday, String gender, String address, HttpSession session, Model model ) {
		
		UserDTO dto = new UserDTO(user_id, user_pw, user_name, email, phone,gender, birthday,address);
		
		String result = service.registerMember(dto);
		model.addAttribute("register_result", result);
		
		//세션에는 로그인 된 사용자 담기
		session.setAttribute("login_user", dto);
		return "registermember";
	}
	
	// 로그아웃 (김)
	@RequestMapping("/logout")
	@ResponseBody
	public void logout(HttpSession session) {
		session.removeAttribute("login_user");
	}
	
	// 마이페이지 (김)
	@RequestMapping("/mypage")
	public String mypage(){
		return "mypage";
	}
	
}
