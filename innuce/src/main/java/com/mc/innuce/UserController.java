package com.mc.innuce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mc.innuce.domain.debate.dto.DebateRoomDTO;
import com.mc.innuce.domain.debate.service.DebateRoomService;
import com.mc.innuce.domain.debate.service.DebateUserService;
import com.mc.innuce.domain.user.dto.MailDTO;
import com.mc.innuce.domain.user.dto.UserDTO;
import com.mc.innuce.domain.user.service.MailService;
import com.mc.innuce.domain.user.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserService service;

	@Autowired
	MailService mail_service;
	
	@Autowired
	DebateUserService debateUserService;
	
	@Autowired
	DebateRoomService debateRoomService;

	// 로그인 페이지 (김)
	@RequestMapping("/login")
	public String login() {
		return "user/login";
	}

	// 로그인 결과 (김)
	@PostMapping("/loginresult")
	@ResponseBody
	public String loginresult(String user_id, String user_pw, boolean rememberId, HttpServletResponse response,
			HttpSession session) {

		// 받은 아이디로 userdto 하나 집어보기

		UserDTO dto = service.selectOneUser(user_id);
		System.out.println(user_id);
		String login_result = null;
		String json_result = null;
		System.out.println(rememberId);

		// 해당 아이디를 가진 회원이 user 테이블에 없을 경우
		if (dto == null) {
			login_result = "아이디나 비밀번호를 확인해주세요.";
			json_result = "{\"login_result\": \"" + login_result + "\" }";
			return json_result;
		}

		// 해당 아이디를 가진 회원이 user 테이블에 있는 경우
		else {
			// 삭제된지 10분 이상인 경우
			if (dto.isUser_isdeleted() && service.overTimeSinceDeleted(dto)) {

				login_result = "회원탈퇴가 완료된 회원입니다. 복구를 위해서는 관리자에게 문의하십시오.";
				json_result = "{\"login_result\": \"" + login_result + "\" }";
				return json_result;
			}
			// 삭제는 되었지만 10분 미만이라 복구가 가능한 경우
			else if (dto.isUser_isdeleted() && !service.overTimeSinceDeleted(dto)) {
				login_result = "회원 복구 페이지로 이동합니다";
				json_result = "{\"login_result\": \"" + login_result + "\" }";
				return json_result;
			}
			// 올바른 비밀번호 입력
			else if (dto.getUser_pw().equals(user_pw)) {
				// 앞으로 세션에 login_user라는 이름으로 userdto 담기
				session.setAttribute("login_user", dto);
				login_result = "로그인 성공.";
				json_result = "{\"login_result\": \"" + login_result + "\" }";

				if (rememberId) {
					Cookie cookie = new Cookie("id", user_id);
					response.addCookie(cookie);
				} else {
					Cookie cookie = new Cookie("id", user_id);
					cookie.setMaxAge(0);

					response.addCookie(cookie);
				}

				return json_result;
			}
			// 다른 비밀번호 입력
			else {
				login_result = "아이디나 비밀번호를 확인해주세요.";
				json_result = "{\"login_result\": \"" + login_result + "\" }";
				return json_result;
			}
		}
	}

	// 회원가입 화면 (김)
	@RequestMapping("/registermember")
	public String registermember() {
		return "user/registermember";
	}

	// id 중복 체크 (김)
	@PostMapping("/idcheck")
	@ResponseBody
	public String idcheck(String user_id) {
		String id_check_result = null;
		String json_result = null;

		UserDTO dto = service.selectOneUser(user_id);
		if (dto != null) {
			id_check_result = "아이디 중복";
			json_result = "{\"id_check_result\": \"" + id_check_result + "\" }";
			return json_result;
		} else {
			id_check_result = "사용가능한 아이디";
			json_result = "{\"id_check_result\": \"" + id_check_result + "\" }";
			return json_result;
		}

	}

	// 회원가입시 이메일 인증
	@PostMapping("/emailcheck")
	@ResponseBody
	public String emailcheck(String email) {
		String json_result = null;
		// 입력받은 email 주소로 MailDTO 하나 만듬
		MailDTO user_email = new MailDTO(email);
		// 메일 인증 번호
		int number_key = mail_service.createMailRegister(user_email);
		json_result = "{\"number_key\": \"" + number_key + "\" }";

		return json_result;
	}

	// 회원가입 처리 (김)
	@PostMapping("/registerresult")
	public String registerProcess(String user_id, String user_pw, String email, String user_name, String phone,
			String birthday, String gender, String address_doro, String address_specific, HttpSession session,
			Model model) {
		String address = address_doro + " " + address_specific;
		UserDTO dto = new UserDTO(user_id, user_pw, user_name, email, phone, gender, birthday, address);

		String result = service.registerMember(dto);
		model.addAttribute("register_result", result);
		System.out.println(result);
		// 세션에는 로그인 된 사용자 담기
		session.setAttribute("login_user", dto);
		return "user/registermember";
	}

	@RequestMapping("/registerResult")
	public String registerResult() {
		return "user/registerResult";
	}

	// 로그아웃 (김)
	@RequestMapping("/logout")
	@ResponseBody
	public void logout(HttpSession session) {
		session.removeAttribute("login_user");
	}

	// 마이페이지 (김)
	@RequestMapping("/mypage")
	public String mypage() {
		return "user/mypage";
	}

	// 마이페이지에서 회원정보 수정누르면 뷰 바꿔주기
	@GetMapping("/mypageChangeinfo")
	public String changeinfo() {
		return "user/mypageChangeinfo";
	}

	// 마이페이지에서 회원정보 수정 내에서 정보 수정 처리 (김)
	@PostMapping("/infochange")
	@ResponseBody
	public void infoChange(String user_pw, String email, String phone, String address, HttpSession session,
			Model model) {
		UserDTO dto = (UserDTO) session.getAttribute("login_user");

		if (user_pw.length() == 0) {
			user_pw = dto.getUser_pw();
		}
		if (email.length() == 0) {
			email = dto.getEmail();
		}
		if (phone.length() == 0) {
			phone = dto.getPhone();
		}
		if (address.length() == 0) {
			address = dto.getAddress();
		}

		dto.setAddress(address);
		dto.setEmail(email);
		dto.setUser_pw(user_pw);
		dto.setPhone(phone);

		service.updateUser(dto);

	}

	// 마이페이지 탈퇴화면 뷰 주기
	@GetMapping("/mypageDelete")
	public String delete() {
		return "user/mypageDelete";
	}

	// 마이페이지 탈퇴기능
	@RequestMapping("/deleteuser")
	@ResponseBody
	public void deleteuser(HttpSession session) {
		UserDTO dto = (UserDTO) session.getAttribute("login_user");
		service.deleteuser(dto);
		session.removeAttribute("login_user");
	}

	

	// 마이페이지 토론방 목록
	@GetMapping("/mypageChatting")
	public ModelAndView myChattingroom(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();

		UserDTO dto = (UserDTO) session.getAttribute("login_user");

		// login id가 참여중인 debate_room_key 목록 생성
		List<Integer> myDebateRoomKeyList = debateUserService.myDebateRoomKeyList(dto.getUser_key());
		int totalCount = debateUserService.myDebateRoomKeyCount(dto.getUser_key());
		
		mv.addObject("page", page);
		mv.addObject("pageCount", 10);
		mv.addObject("totalCount", totalCount);

		if (myDebateRoomKeyList == null || myDebateRoomKeyList.isEmpty()) {
			mv.setViewName("user/mypageChattingroom");
			return mv;
		}

		int start = (page - 1) * 10;
		int end = (page - 1) * 10 + 10;
		if (end >= myDebateRoomKeyList.size())
			end = myDebateRoomKeyList.size();
		List<Integer> subMyDebateRoomKeyList = myDebateRoomKeyList.subList(start, end);

		// debate_room_status 가 2 또는 1인 목록
		List<DebateRoomDTO> myDebateRoomList = debateRoomService.myDebateRoomList(subMyDebateRoomKeyList);

		// debate_room_key 목록으로 해당하는 방의 실시간 참여자 수 목록 반환
		List<Integer> myDebateRoomUserConnectCountList = debateUserService
				.openDebateRoomUserConnectCountList(subMyDebateRoomKeyList);
		// debate_room_key 목록으로 해당하는 방의 전체 참여자 수 목록 반환
		List<Integer> myDebateRoomUserCountList = debateUserService.openDebateRoomUserCountList(subMyDebateRoomKeyList);

		mv.addObject("openDebateRoomList", myDebateRoomList);
		mv.addObject("openDebateRoomUserConnectCountList", myDebateRoomUserConnectCountList);
		mv.addObject("openDebateRoomUserCountList", myDebateRoomUserCountList);
		mv.setViewName("user/mypageChattingroom");

		return mv;
	}

	// 회원 복구 페이지
	@RequestMapping("/restoreuser")
	public String restoreuser() {
		return "user/restoreuser";
	}

	// 회원복구 처리
	@PostMapping("/restoreresult")
	@ResponseBody
	public String restoreresult(HttpSession session, String user_id, String user_pw) {
		UserDTO dto = service.selectOneUser(user_id);
		String restore_result = null;
		String json_result = null;

		// 해당 아이디를 가진 회원이 user 테이블에 없을 경우
		if (dto == null) {
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
		else if (service.overTimeSinceDeleted(dto)) {
			restore_result = "복구 기한이 지난 아이디입니다.";
			json_result = "{\"restore_result\": \"" + restore_result + "\" }";
			return json_result;
		} else {
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
	@GetMapping("/idfindresult")
	public ModelAndView idfindresult(@RequestParam(value = "pagenum", required = false, defaultValue = "1") int pagenum,
			@RequestParam String user_name, @RequestParam String email) {
		// 해당 이름, 이메일을 가진 UserDTO의 id를 리스트로 다 가져오기
		String[] id_list = service.selectUserId(user_name, email);
		// 해당 이름, 이메일을 가진 UserDTO 숫자
		int id_list_length = id_list.length;
		// 한페이지에 보여줄 user 수
		int user_in_page = 5;
		// 마지막 페이지에 보여줄 user 수
		int user_in_last_page;
		if (id_list_length % user_in_page == 0) {
			user_in_last_page = user_in_page;
		} else {
			user_in_last_page = id_list_length % user_in_page;
		}
		// 총 페이지 숫자
		int page_count = id_list_length / user_in_page + 1;

		// sql 쿼리시 limit x,y 절에서 시작해야될 포인트(x)
		int starting_point = (pagenum - 1) * user_in_page;
		// 해당 pagenum에 user_in_page 만큼 user_id들을 담고있는 리스트
		String[] id_paging_list = service.selectPagingId(user_name, email, starting_point, user_in_page);
		// 해당 pagenum에 user_in_page 만큼 regdate들을 담고있는 리스트
		String[] regdate_paging_list = service.selectPagingRegdate(user_name, email, starting_point, user_in_page);

		String result;

		// 해당되는 이름 이메일을 가진 userdto가 없는경우
		if (id_list_length == 0) {
			result = "검색된 결과가 없습니다" + "<br>";
			result += "<input type='button' value='이전페이지로 이동' id='back_button' onclick ='location.href =&quot;idpwfind &quot;'>";
		}

		// 해당하는 조건의 userdto가 있는경우
		else {
			result = "<table id= 'id_search_table'>"
					+ "<thead><tr> <th> 가입된 아이디</th> <th> 가입일자 </th> </tr></thead><tbody>";
			// 마지막 페이지가 아니여서 각 테이블에 user_in_page 수 만큼 보여주는 경우
			if (pagenum != page_count) {
				for (int i = 0; i < user_in_page; i++) {
					result += "<tr> <td>" + id_paging_list[i] + "</td><td>" + regdate_paging_list[i] + "</td>";
				}
			}
			// 마지막 페이지인 경우
			else {
				for (int i = 0; i < user_in_last_page; i++) {
					result += "<tr> <td>" + id_paging_list[i] + "</td><td>" + regdate_paging_list[i] + "</td>";
				}
			}

			result += "</tbody></table>";

			// 밑에 페이지 이동 링크
			for (int i = 0; i < page_count; i++) {
				result += "<a href=idfindresult?pagenum=" + (i + 1) + "&user_name=" + user_name + "&email=" + email
						+ ">" + (i + 1) + "</a>";
			}
			// 비밀번호 찾기 페이지 이동 버튼
			result += "<br><input type='button' value='비밀번호 찾기 페이지로 이동' id='pw_find_button' onclick ='location.href =&quot;pwfind &quot;'>";
		} // else

		ModelAndView mv = new ModelAndView();

		mv.addObject("html_result", result);
		mv.setViewName("user/idfindresult");
		return mv;
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
		if (service.selectOneUser(user_id) == null) {
			pw_find_result = "해당하는 아이디를 가진 회원이 없습니다.";
		}
		// 삭제된 회원
		else if (service.selectOneUser(user_id).isUser_isdeleted() == true) {
			pw_find_result = "회원 탈퇴를 한 회원입니다.";
		}
		// 이름 또는 이메일이 DB 정보와 다른 경우
		else if (!service.selectOneUser(user_id).getUser_name().equals(user_name)
				|| !service.selectOneUser(user_id).getEmail().equals(email)) {
			pw_find_result = "이름 또는 이메일을 확인해 주세요";
			System.out.println(service.selectOneUser(user_id).getUser_name());
			System.out.println(service.selectOneUser(user_id).getEmail());

		}

		else {
			mail_service.pwfind(service.selectOneUser(user_id));

			pw_find_result = "가입 당시 입력하신 이메일로 비밀번호가 발송되었습니다.";
		}
		json_result = "{\"pw_find_result\": \"" + pw_find_result + "\" }";

		return json_result;
	}

	// 테스트페이지 (김)
	@RequestMapping("/testpage")
	public String testpage() {
		return "testchange";
	}

	// 관리자 마이페이지 화면
	@RequestMapping("/adminpage")
	public String adminpage() {
		return "user/adminpage";
	}

	// 관리자 회원관리 기능
	@GetMapping("/usermanage")
	@ResponseBody
	public String usermanage(@RequestParam(value = "pagenum", required = false, defaultValue = "1") int pagenum,
			@RequestParam(value = "search_id", required = false, defaultValue = "") String user_id,
			@RequestParam(value = "search_name", required = false, defaultValue = "") String user_name) {
		String result;
		String json_result;
		// 전체 유저 수
		int total_user;

		// 관리자 화면에서 유저 이름 / 유저 id를 따로 검색하지 않은 경우
		if (user_id.length() == 0 && user_name.length() == 0) {
			total_user = service.selectAllUser();
		}
		// 관리자 화면에서 id로 검색을 한 경우 id는 유니크하기 때문에 total_user가 1 or 0이 나올꺼임
		else if (user_id.length() != 0 && user_name.length() == 0) {
			total_user = service.selectAllUserById(user_id);
		}
		// 관리자 화면에서 이름으로 검색을 한 경우
		else if (user_id.length() == 0 && user_name.length() != 0) {
			total_user = service.selectAllUserByUserName(user_name);
		}
		// 위에서 모든 경우를 다 나눴기 때문에 total_user 이 분기를 탈 일은 없음
		else {
			total_user = 0;
		}

		// 한페이지에 보여줄 user 수
		int user_in_page = 10;

		// 마지막 페이지에 보여줄 user 수
		int user_in_last_page;
		if (total_user % user_in_page == 0) {
			user_in_last_page = user_in_page;
		} else {
			user_in_last_page = total_user % user_in_page;
		}

		// 총 페이지 숫자
		int page_count = total_user / user_in_page + 1;

		// sql 쿼리시 limit x,y 절에서 시작해야될 포인트(x)
		int starting_point = (pagenum - 1) * user_in_page;

		// user_dto들 다 담아온 리스트
		List<UserDTO> user_list;

		// 관리자 화면에서 유저 이름 / 유저 id를 따로 검색하지 않은 경우
		if (user_id.length() == 0 && user_name.length() == 0) {
			user_list = service.selectAllUserPagingList(user_in_page, starting_point);
		}
		// 관리자 화면에서 id로 검색을 한 경우 id는 유니크하기 때문에 user_list의 size가 1 or 0이 나올꺼임
		else if (user_id.length() != 0 && user_name.length() == 0) {
			user_list = service.selectAllUserPagingListById(user_id, user_in_page, starting_point);
		}
		// 관리자 화면에서 이름으로 검색을 한 경우
		else if (user_id.length() == 0 && user_name.length() != 0) {
			user_list = service.selectAllUserPagingListByUserName(user_name, user_in_page, starting_point);
		}
		// 위에서 모든 경우를 다 나눴기 때문에 total_user 이 분기를 탈 일은 없음
		else {
			user_list = null;
		}

		// 검색조건의 회원이없는경우
		if (user_list.size() == 0 || total_user == 0) {
			result = "해당하는 회원이 없습니다 <br>";
			// 검색옵션 및 검색어 페이지
			result += "<div id='search_board_box'>";
			result += "<select id='search_option'>" + "<option value='name'>이름</option>"
					+ "<option value='id'>아이디</option>" + "</select>"
					+ "<input type='text', placeholder='검색어입력', id='search'>"
					+ "<button id='search_button' type='button'>검색 </button>";
			result += "</div>";

			// 검색옵션을 통한 검색 ajax처리
			result += "<script>" + "$('#search_button').on('click',function(){" +

			// 아이디로 검색했을경우
					"if($('#search_option option:selected').val() == 'id'){" + "$.ajax({"
					+ "url: 'usermanage?&search_id='+ $('#search').val()," + "type: 'get'," + "dataType: 'json',"
					+ "success : function(response){" + "$('#adminPage_main').html(response.member_table)" + "}" + // success
					"})" + // ajax
					"}" + // if
					// 이름으로 검색했을경우
					"else{" + "$.ajax({" + "url: 'usermanage?&search_name='+$('#search').val()," + "type: 'get',"
					+ "dataType: 'json'," + "success : function(response){"
					+ "$('#adminPage_main').html(response.member_table);" + "}" + // success
					"})" + // ajax
					"}" + // else
					"})"; // on
		} else {
			// html로 테이블 만들기
			result = "<table id= 'id_search_table'>"
					+ "<thead> <tr> <th>회원id</th> <th>회원이름</th> <th>가입일자</th><th>강제추방</th></tr></thead> <tbody>";
			// 마지막 페이지가 아니여서 각 테이블에 user_in_page 수 만큼 보여주는 경우
			// 마지막 td의 버튼은 누르면 회원 탈퇴 기능
			if (pagenum != page_count) {
				for (int i = 0; i < user_in_page; i++) {
					result += "<tr> <td>" + user_list.get(i).getUser_id() + "</td><td>"
							+ user_list.get(i).getUser_name() + "</td><td>" + user_list.get(i).getRegdate()
							+ "</td><td>" + "<button class = 'admin_button' id='button" + i + "'user_id ='"
							+ user_list.get(i).getUser_id() + "'> 강제탈퇴</button>" + "</td></tr>";

				}
			}
			// 마지막 페이지인 경우
			else {
				for (int i = 0; i < user_in_last_page; i++) {

					result += "<tr> <td>" + user_list.get(i).getUser_id() + "</td><td>"
							+ user_list.get(i).getUser_name() + "</td><td>" + user_list.get(i).getRegdate()
							+ "</td><td>" + "<button class = 'admin_button' id='button" + i + "'user_id ='"
							+ user_list.get(i).getUser_id() + "'> 강제탈퇴</button>" + "</td></tr>";
				}
			}
			// 테이블 마무리
			result += "</tbody></table>";

			// 각각의 회원 탈퇴 버튼 눌렀을떄의 ajax script를 html에 붙이기
			if (pagenum != page_count) {
				for (int i = 0; i < user_in_page; i++) {
					result += "<script>" + "$('#button" + i + "').on('click',function(){"
							+ "result = window.confirm('정말로 해당유저를 삭제하시겠습니까?');" + "if(result ==true){"
							// 정말로 삭제한다고 했을때 ajax 부르기
							+ "$.ajax({" + "url : 'admindelete', "
							// data : { 'user_id' : $("#user_id").val()},
							+ " data :{ 'user_id' : $('#button" + i + "').attr('user_id')}," + "type : 'post',"
							+ "success : function(response){" + "alert('회원삭제가 완료되었습니다');" + "location.reload(true)"
							+ "}"// success
							+ "})"// ajax
							+ "}"// if
							+ "})" // on
							+ "</script>";
				}
			} else {
				for (int i = 0; i < user_in_last_page; i++) {
					result += "<script>" + "$('#button" + i + "').on('click',function(){"
							+ "result = window.confirm('정말로 해당유저를 삭제하시겠습니까?');" + "if(result ==true){"
							// 정말로 삭제한다고 했을때 ajax 부르기
							+ "$.ajax({" + "url : 'admindelete', "
							// data : { 'user_id' : $("#user_id").val()},
							+ " data :{ 'user_id' : $('#button" + i + "').attr('user_id')}," + "type : 'post',"
							+ "success : function(response){" + "alert('회원삭제가 완료되었습니다');" + "location.reload(true)"
							+ "}"// success
							+ "})"// ajax
							+ "}"// if
							+ "})" // on
							+ "</script>";
				}
			}

			// 밑에 페이지 이동 링크
			result += "<div id= 'a_tags_box'>";

			for (int i = 0; i < page_count; i++) {
				result += "<a id=" + (i + 1) + ">" + (i + 1) + "</a>" + "<script>" + "$('#" + (i + 1)
						+ "').on('click',function(){" + "$.ajax({" + "url:'usermanage?pagenum='+$('#" + (i + 1)
						+ "').html()+'&search_name=" + user_name + "&search_id=" + user_id + "' ," + "type: 'get',"
						+ "dataType: 'json'," + "success : function(response){"
						+ "$('#adminPage_main').html(response.member_table)" + "}" + // success
						"})" + // ajax
						"})" + // on
						"</script>";
			}
			result += "</div>";

			// 검색옵션 및 검색어 페이지
			result += "<div id='search_board_box'>";
			result += "<select id='search_option'>" + "<option value='name'>이름</option>"
					+ "<option value='id'>아이디</option>" + "</select>"
					+ "<input type='text', placeholder='검색어입력', id='search'>"
					+ "<button id='search_button' type='button'>검색 </button>";
			result += "</div>";

			// 검색옵션을 통한 검색 ajax처리
			result += "<script>" + "$('#search_button').on('click',function(){" +

			// 아이디로 검색했을경우
					"if($('#search_option option:selected').val() == 'id'){" + "$.ajax({"
					+ "url: 'usermanage?&search_id='+ $('#search').val()," + "type: 'get'," + "dataType: 'json',"
					+ "success : function(response){" + "$('#adminPage_main').html(response.member_table)" + "}" + // success
					"})" + // ajax
					"}" + // if
					// 이름으로 검색했을경우
					"else{" + "$.ajax({" + "url: 'usermanage?&search_name='+$('#search').val()," + "type: 'get',"
					+ "dataType: 'json'," + "success : function(response){"
					+ "$('#adminPage_main').html(response.member_table);" + "}" + // success
					"})" + // ajax
					"}" + // else
					"})"; // on

		}
		json_result = "{\"member_table\": \"" + result + "\" }";

		return json_result;
	}

	// 관리자 페이지에서 회원 강제탈퇴
	@PostMapping("/admindelete")
	@ResponseBody
	public void admindelete(String user_id) {
		UserDTO dto = service.selectOneUser(user_id);
		service.deleteuser(dto);
	}

	// 관리자 페이지에서 탈퇴회원관리
	@GetMapping("/userrestoremanage")
	@ResponseBody
	public String userrestoremanage(@RequestParam(value = "pagenum", required = false, defaultValue = "1") int pagenum,
			@RequestParam(value = "search_id", required = false, defaultValue = "") String user_id,
			@RequestParam(value = "search_name", required = false, defaultValue = "") String user_name) {
		String result;
		String json_result;

		// 전체 삭제된 유저 수
		int total_deleted_user;
		// 관리자 화면에서 유저 이름 / 유저 id를 따로 검색하지 않은 경우
		if (user_id.length() == 0 && user_name.length() == 0) {
			total_deleted_user = service.selectAllDeletedUser();
		}
		// 관리자 화면에서 id로 검색을 한 경우 id는 유니크하기 때문에 total_user가 1 or 0이 나올꺼임
		else if (user_id.length() != 0 && user_name.length() == 0) {
			total_deleted_user = service.selectAllDeletedUserById(user_id);
		}
		// 관리자 화면에서 이름으로 검색을 한 경우
		else if (user_id.length() == 0 && user_name.length() != 0) {
			total_deleted_user = service.selectAllDeletedUserByUserName(user_name);
		}
		// 위에서 모든 경우를 다 나눴기 때문에 total_user 이 분기를 탈 일은 없음
		else {
			total_deleted_user = 0;
		}

		// 한페이지에 보여줄 user 수
		int user_in_page = 10;

		// 마지막 페이지에 보여줄 user 수
		int user_in_last_page;
		if (total_deleted_user % user_in_page == 0) {
			user_in_last_page = user_in_page;
		} else {
			user_in_last_page = total_deleted_user % user_in_page;
		}

		// 총 페이지 숫자
		int page_count = total_deleted_user / user_in_page + 1;

		// sql 쿼리시 limit x,y 절에서 시작해야될 포인트(x)
		int starting_point = (pagenum - 1) * user_in_page;

		// user_dto들 다 담아온 리스트
		List<UserDTO> deleted_user_list = service.selectAllDeletedUserPagingList(user_in_page, starting_point);

		// 관리자 화면에서 유저 이름 / 유저 id를 따로 검색하지 않은 경우
		if (user_id.length() == 0 && user_name.length() == 0) {
			deleted_user_list = service.selectAllDeletedUserPagingList(user_in_page, starting_point);
		}
		// 관리자 화면에서 id로 검색을 한 경우 id는 유니크하기 때문에 user_list의 size가 1 or 0이 나올꺼임
		else if (user_id.length() != 0 && user_name.length() == 0) {
			deleted_user_list = service.selectAllDeletedUserPagingListById(user_id, user_in_page, starting_point);
		}
		// 관리자 화면에서 이름으로 검색을 한 경우
		else if (user_id.length() == 0 && user_name.length() != 0) {
			deleted_user_list = service.selectAllDeletedUserPagingListByUserName(user_name, user_in_page,
					starting_point);
		}
		// 위에서 모든 경우를 다 나눴기 때문에 total_user 이 분기를 탈 일은 없음
		else {
			deleted_user_list = null;
		}
		// 삭제된 회원이 없는 경우
		if (deleted_user_list.size() == 0 || total_deleted_user == 0) {
			result = "회원이 없습니다<br>";
			// 검색옵션 및 검색어 페이지
			result += "<div id='search_board_box'>";
			result += "<select id='search_option'>" + "<option value='name'>이름</option>"
					+ "<option value='id'>아이디</option>" + "</select>"
					+ "<input type='text', placeholder='검색어입력', id='search'>"
					+ "<button id='search_button' type='button'>검색 </button>";
			result += "</div>";

			// 검색옵션을 통한 검색 ajax처리
			result += "<script>" + "$('#search_button').on('click',function(){" +

			// 아이디로 검색했을경우
					"if($('#search_option option:selected').val() == 'id'){" + "$.ajax({"
					+ "url: 'userrestoremanage?&search_id='+ $('#search').val()," + "type: 'get'," + "dataType: 'json',"
					+ "success : function(response){" + "$('#adminPage_main').html(response.member_table)" + "}" + // success
					"})" + // ajax
					"}" + // if
					// 이름으로 검색했을경우
					"else{" + "$.ajax({" + "url: 'admindelete?&search_name='+$('#search').val()," + "type: 'get',"
					+ "dataType: 'json'," + "success : function(response){"
					+ "$('#adminPage_main').html(response.member_table);" + "}" + // success
					"})" + // ajax
					"}" + // else
					"})"; // on
		} else {
			// html로 테이블 만들기
			result = "<table id= 'id_search_table'>"
					+ "<thead> <tr> <th>회원id</th> <th>회원이름</th> <th>탈퇴일자</th><th>회원복구</th></tr></thead> <tbody>";
			// 마지막 페이지가 아니여서 각 테이블에 user_in_page 수 만큼 보여주는 경우
			// 마지막 td의 버튼은 누르면 회원 탈퇴 기능
			if (pagenum != page_count) {
				for (int i = 0; i < user_in_page; i++) {
					result += "<tr> <td>" + deleted_user_list.get(i).getUser_id() + "</td><td>"
							+ deleted_user_list.get(i).getUser_name() + "</td><td>"
							+ deleted_user_list.get(i).getDeleted_time() + "</td><td>"
							+ "<button class = 'admin_button' id='button" + i + "'user_id ='"
							+ deleted_user_list.get(i).getUser_id() + "'> 강제복구</button>" + "</td></tr>";

				}
			}
			// 마지막 페이지인 경우
			else {
				for (int i = 0; i < user_in_last_page; i++) {
					result += "<tr> <td>" + deleted_user_list.get(i).getUser_id() + "</td><td>"
							+ deleted_user_list.get(i).getUser_name() + "</td><td>"
							+ deleted_user_list.get(i).getDeleted_time() + "</td><td>"
							+ "<button class = 'admin_button' id='button" + i + "'user_id ='"
							+ deleted_user_list.get(i).getUser_id() + "'> 강제복구</button>" + "</td></tr>";
				}
			}
			// 테이블 마무리
			result += "</tbody></table>";

			// 각각의 회원 탈퇴 복구 눌렀을떄의 ajax script를 html에 붙이기
			if (pagenum != page_count) {
				for (int i = 0; i < user_in_page; i++) {
					result += "<script>" + "$('#button" + i + "').on('click',function(){"
							+ "result = window.confirm('정말로 해당유저를 복구하시겠습니까?');" + "if(result ==true){"
							// 정말로 삭제한다고 했을때 ajax 부르기
							+ "$.ajax({" + "url : 'adminrestore', "
							// data : { 'user_id' : $("#user_id").val()},
							+ " data :{ 'user_id' : $('#button" + i + "').attr('user_id')}," + "type : 'post',"
							+ "success : function(response){" + "alert('회원복구가 완료되었습니다');" + "location.reload(true)"
							+ "}"// success
							+ "})"// ajax
							+ "}"// if
							+ "})" // on
							+ "</script>";
				}
			} else {
				for (int i = 0; i < user_in_last_page; i++) {
					result += "<script>" + "$('#button" + i + "').on('click',function(){"
							+ "result = window.confirm('정말로 해당유저를 복구하시겠습니까?');" + "if(result ==true){"
							// 정말로 삭제한다고 했을때 ajax 부르기
							+ "$.ajax({" + "url : 'adminrestore', "
							// data : { 'user_id' : $("#user_id").val()},
							+ " data :{ 'user_id' : $('#button" + i + "').attr('user_id')}," + "type : 'post',"
							+ "success : function(response){" + "alert('회원복구가 완료되었습니다');" + "location.reload(true)"
							+ "}"// success
							+ "})"// ajax
							+ "}"// if
							+ "})" // on
							+ "</script>";
				}
			}
			// 밑에 페이지 이동 링크
			result += "<div id= 'a_tags_box'>";

			for (int i = 0; i < page_count; i++) {
				result += "<a id=" + (i + 1) + ">" + (i + 1) + "</a>" + "<script>" + "$('#" + (i + 1)
						+ "').on('click',function(){" + "$.ajax({" + "url:'userrestoremanage?pagenum='+$('#" + (i + 1)
						+ "').html()," + "type: 'get'," + "dataType: 'json'," + "success : function(response){"
						+ "$('#adminPage_main').html(response.member_table)" + "}" + // success
						"})" + // ajax
						"})" + // on
						"</script>";
			}
			result += "</div>";

			// 검색옵션 및 검색어 페이지
			result += "<div id='search_board_box'>";
			result += "<select id='search_option'>" + "<option value='name'>이름</option>"
					+ "<option value='id'>아이디</option>" + "</select>"
					+ "<input type='text', placeholder='검색어입력', id='search'>"
					+ "<button id='search_button' type='button'>검색 </button>";
			result += "</div>";

			// 검색옵션을 통한 검색 ajax처리
			result += "<script>" + "$('#search_button').on('click',function(){" +

			// 아이디로 검색했을경우
					"if($('#search_option option:selected').val() == 'id'){" + "$.ajax({"
					+ "url: 'userrestoremanage?search_id='+ $('#search').val()," + "type: 'get'," + "dataType: 'json',"
					+ "success : function(response){" + "$('#adminPage_main').html(response.member_table)" + "}" + // success
					"})" + // ajax
					"}" + // if
					// 이름으로 검색했을경우
					"else{" + "$.ajax({" + "url: 'userrestoremanage?search_name='+$('#search').val()," + "type: 'get',"
					+ "dataType: 'json'," + "success : function(response){"
					+ "$('#adminPage_main').html(response.member_table);" + "}" + // success
					"})" + // ajax
					"}" + // else
					"})"; // on
		}

		json_result = "{\"member_table\": \"" + result + "\" }";

		return json_result;
	}

	// 관리자 페이지에서 회원 강제복구
	@PostMapping("/adminrestore")
	@ResponseBody
	public void adminrestore(String user_id) {
		UserDTO dto = service.selectOneUser(user_id);
		service.restoreUser(dto);
	}

}
