package com.mc.innuce;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
		return "user/login";
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
		if (dto == null ) {
			login_result = "해당 아이디를 가진 회원이 없습니다.";
			json_result = "{\"login_result\": \"" + login_result + "\" }";
			return json_result;
		}
		
		// 해당 아이디를 가진 회원이 user 테이블에 있는 경우
		else {
			// 삭제된지 10분 이상인 경우 
			if(dto.isUser_isdeleted() && service.overTimeSinceDeleted(dto)) {
				login_result = "회원탈퇴가 완료된 회원입니다.";
				json_result = "{\"login_result\": \"" + login_result + "\" }";
				return json_result;
			}
			// 삭제는 되었지만 10분 미만이라 복구가 가능한 경우
			else if(dto.isUser_isdeleted() && !service.overTimeSinceDeleted(dto) ) {
				login_result ="회원 복구 페이지로 이동합니다";
				json_result = "{\"login_result\": \"" + login_result + "\" }";
				return json_result;
			}
			// 올바른 비밀번호 입력
			else if(dto.getUser_pw().equals(user_pw)) {
				// 앞으로 세션에 login_user라는 이름으로 userdto 담기
				session.setAttribute("login_user", dto);
				login_result = "로그인 성공.";
				json_result = "{\"login_result\": \"" + login_result + "\" }";
				return json_result;
			}
			// 다른 비밀번호 입력
			else {
				System.out.println(dto.getUser_id());
				System.out.println(dto.getEmail());
				System.out.println(dto.getUser_pw());
				login_result = "잘못된 비밀번호를 입력하셨습니다.";
				json_result = "{\"login_result\": \"" + login_result + "\" }";
				return json_result;
			}
		}
	}
	
	// 회원가입 화면 (김)
	@RequestMapping("/registermember")
	public String registermember(){
		return "user/registermember";
	}
	
	// id 중복 체크 (김)
	@PostMapping("/idcheck")
	@ResponseBody
	public String idcheck(String user_id) {
		String id_check_result = null;
		String json_result =null;
		
		UserDTO dto = service.selectOneUser(user_id);
		if(dto != null) {
			id_check_result = "아이디 중복";
			json_result = "{\"id_check_result\": \"" + id_check_result + "\" }";
			return json_result;
		}
		else {
			id_check_result = "사용가능한 아이디";
			json_result = "{\"id_check_result\": \"" + id_check_result + "\" }";
			return json_result;
		}
		
		
	}
	// 회원가입 처리 (김)
	@PostMapping("/registerresult")
	public String registerProcess(String user_id,String user_pw, String email, String user_name, String phone,
			String birthday, String gender, String address_doro, String address_specific, HttpSession session, Model model ) {
		String address = address_doro+ " "+ address_specific;
		UserDTO dto = new UserDTO(user_id, user_pw, user_name, email, phone,gender, birthday,address);
		
		String result = service.registerMember(dto);
		model.addAttribute("register_result", result);
		System.out.println(result);
		//세션에는 로그인 된 사용자 담기
		session.setAttribute("login_user", dto);
		return "user/registermember";
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
		return "user/mypage";
	}
	//회원정보 수정 (김)
	@PostMapping("/infochange")
	@ResponseBody
	public void infoChange(String user_pw, String email,  String phone, String address, HttpSession session, Model model){
		UserDTO dto = (UserDTO) session.getAttribute("login_user");
		
		if(user_pw.length() == 0) {
			user_pw = dto.getUser_pw();
		}
		if(email.length() == 0) {
			email = dto.getEmail();
		}
		if(phone.length() == 0) {
			phone = dto.getPhone();
		}
		if(address.length() == 0) {
			address = dto.getAddress();
		}
		
		dto.setAddress(address);
		dto.setEmail(email);
		dto.setUser_pw(user_pw);
		dto.setPhone(phone);
		
		System.out.println(address);
		System.out.println(email);
		System.out.println(user_pw);
		System.out.println(phone);
		
		service.updateUser(dto);
		
	}
	
	// 탈퇴기능
	@RequestMapping("/deleteuser")
	@ResponseBody
		public void delete(HttpSession session) {
			UserDTO dto = (UserDTO) session.getAttribute("login_user");
			dto.setUser_isdeleted(true);
			service.deleteuser(dto);
			System.out.println(service.selectUserDeletedTime(dto));
			dto.setDeleted_time(service.selectUserDeletedTime(dto));
			System.out.println(dto.getDeleted_time());
			System.out.println(dto.isUser_isdeleted());
			session.removeAttribute("login_user");
	}
	
	// 회원 복구 페이지
	@RequestMapping("/restoreuser")
	public String restoreuser() {
		return "user/restoreuser";
	}
	
	//회원복구 처리
	@PostMapping("/restoreresult")
	@ResponseBody
	public String restoreresult(HttpSession session, String user_id, String user_pw) {
		UserDTO dto = service.selectOneUser(user_id);
		String restore_result = null;
		String json_result =null;
		
		// 해당 아이디를 가진 회원이 user 테이블에 없을 경우
		if (dto == null ) {
			restore_result = "해당 아이디를 가진 회원이 없습니다.";
			json_result = "{\"restore_result\": \"" + restore_result + "\" }";
			return json_result;
		}
		// 입력한 비밀번호가 틀릴경우
		else if (!dto.getUser_pw().equals(user_pw)) {
			System.out.println(user_pw);
			System.out.println(dto.getUser_pw());
			restore_result = "올바른 비밀번호를 입력해주세요.";
			json_result = "{\"restore_result\": \"" + restore_result + "\" }";
			return json_result;
		}
		// 회원탈퇴한지 10분 넘었을 경우
		else if(service.overTimeSinceDeleted(dto)) {
			restore_result = "복구 기한이 지난 아이디입니다.";
			json_result = "{\"restore_result\": \"" + restore_result + "\" }";
			return json_result;
		}
		else {
			service.restoreUser(dto);
			System.out.println(dto.isUser_isdeleted());
			System.out.println(dto.getDeleted_time());
			restore_result = "회원 복구에 성공했습니다.";
			json_result = "{\"restore_result\": \"" + restore_result + "\" }";
			session.setAttribute("login_user", dto);
			return json_result;
		}
	}
	// 아이디/ 비밀번호 찾기 페이지
	@RequestMapping("/idpwfind")
	public String idpwsearch() {
		return "user/idpwfind";
	}
	// 아이디 찾기
		@RequestMapping("/idfind")
		public String idfind() {
			return "user/idfind";
		}
	// 아이디 찾기 결과
	@PostMapping("/idfindresult")
	@ResponseBody
	public List< String[]> idfindresult(String user_name,String email) {
		//해당 이름, 이메일을 가진 UserDTO의 id와 가입일자를 리스트로 다 가져오기
		String[] id_list = service.selectUserId(user_name,email);
		String[] regdate_list = service.selectRegdate(user_name,email);
		List< String[]> result = new ArrayList<>();
		result.add(id_list);
		result.add(regdate_list);
		return result;
	}
	
	// 비밀번호 찾기
	@RequestMapping("/pwfind")
	public String pwfind() {
		return "user/pwfind";
	}
	// 비밀번호 찾기 결과
	@PostMapping("/pwfindresult")
	@ResponseBody
	public String pwfindresult(String user_id, String user_name, String email) {
		String json_result = null;
		String pw_find_result = null;
		// 해당하는 아이디가 없는 경우
		if(service.selectOneUser(user_id)==null) {
			pw_find_result = "해당하는 아이디를 가진 회원이 없습니다.";
		}
		// 삭제된 회원
		else if(service.selectOneUser(user_id).isUser_isdeleted()==true) {
			pw_find_result = "회원 탈퇴를 한 회원입니다.";
		}
		// 이름 또는 이메일이 DB 정보와 다른 경우
		else if(!service.selectOneUser(user_id).getUser_name().equals( user_name) || !service.selectOneUser(user_id).getEmail().equals(email)) {
			pw_find_result = "이름 또는 이메일을 확인해 주세요";
			System.out.println(service.selectOneUser(user_id).getUser_name());
			System.out.println(service.selectOneUser(user_id).getEmail());
		}
		else {
			pw_find_result ="가입 당시 입력하신 이메일로 비밀번호가 발송되었습니다.";
		}
		json_result = "{\"pw_find_result\": \"" + pw_find_result + "\" }";
		
		return json_result;
	}
	
	// 테스트페이지 (김)
	@RequestMapping("/testpage")
	public String testpage(){
		return "testchange";
	}
	
	//
}
