package com.mc.innuce.domain.user.service;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.user.dao.UserDAO;
import com.mc.innuce.domain.user.dto.MailDTO;
import com.mc.innuce.domain.user.dto.UserDTO;

import jakarta.activation.FileDataSource;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {
	
	@Autowired
	UserDAO dao;
	
	private final JavaMailSender javaMailSender;
	
	// 메일 주소로 메일 만들고 인증번호 리턴하기
	public int createMailRegister(MailDTO user_email) {
		 	
		String sender_email = "noreply@innuce.com";
		// 6자리 임시 인증번호
		int number_key = ThreadLocalRandom.current().nextInt(100000, 1000000);;
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		// 이메일 내용
		String email_content =
				"<h3>요청하신 인증 번호입니다.</h3>"
				+"<img src= 'http://27.96.131.135:8080/images/inNUCE_logo.png'>"
				+"<span> [인누케]<br/> 호두(껍데기) 안에 라는 뜻의 라틴어<br/> </span>"
				+" <span> = 한 마디로 요약하면<br/> </span>"
				+ "<h2>"+ number_key + "</h2>"
				+ "<div> 해당 인증 번호를 인증번호 확인란에 기입해 주세요</div>";
		
        try {
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setSubject("inNUCE 회원가입 이메일 인증 관련 이메일입니다");
        messageHelper.setTo(user_email.getReceiver());
        messageHelper.setFrom(sender_email);
        messageHelper.setText(email_content, true);
        javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("이메일 발송 에러");
        }
        
		return number_key;	
	}
	
	// 임시비밀번호 발급해주고 그 비밀번호로 유저 비밀번호 바꿔주기
	public void pwfind(UserDTO dto) {
		
		String sender_email = "noreply@innuce.com";
		// 6자리 임시 인증번호
		int number_key = ThreadLocalRandom.current().nextInt(100000, 1000000);;
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		// 이메일 내용
		String email_content =
				"<h3>발급된 임시비밀번호입니다.</h3>"
				+"<img src= 'http://27.96.131.135:8080/images/inNUCE_logo.png'>"
				+"<span> [인누케]<br/> 호두(껍데기) 안에 라는 뜻의 라틴어<br/> </span>"
				+" <span> = 한 마디로 요약하면<br/> </span>"
				+ "<h2>"+ number_key + "</h2>"
				+ "<div> 해당 비밀번호를 통해 로그인 후 비밀번호를 변경해주세요.</div>";
		
        try {
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setSubject("inNUCE 임시 비밀번호 발급 관련 이메일입니다");
        messageHelper.setTo(dto.getEmail());
        messageHelper.setFrom(sender_email);
        messageHelper.setText(email_content, true);
        javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("이메일 발송 에러");
        }
       
		
		// 서버단에서 비밀번호 바꿔주기
		dto.setUser_pw(Integer.toString(number_key));
		
		// db에서 비밀번호 바꿔주기
		HashMap map = new HashMap<>();
		map.put("user_key",dto.getUser_key());
		map.put("user_pw",number_key);
		dao.changePw(map);
		System.out.println("서비스 통과");
		
	}

}

	