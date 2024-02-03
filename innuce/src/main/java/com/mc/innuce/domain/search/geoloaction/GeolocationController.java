package com.mc.innuce.domain.search.geoloaction;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mc.innuce.domain.search.service.GeolocationService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class GeolocationController {
	@Autowired
	GeolocationService service;

	@RequestMapping("/geo")
	String searchMyPlace() {
		return "geo";
	}

	@RequestMapping("/geo2")
	ModelAndView searchMyPlace(String ip, HttpServletRequest request) {
		if (ip == null || ip.equals("")) {
			ip = request.getRemoteAddr();// 현재 클라이언트 ip
		}
		System.out.println("====" + ip + "====");

		ModelAndView mv = new ModelAndView();
		String json = service.test(ip);
		mv.addObject("result", json);
		mv.setViewName("geo2");
		return mv;
	}

	@GetMapping("/geoToip")
	@ResponseBody
	String toIp(String url) throws Exception {
		String ip = InetAddress.getByName(url).getHostAddress();
		System.out.println("geoToip: "+ip);
		return "{\"ip\" : \"" + ip + "\"}";
	}
//	IPv4 vs IPv6

//	컴퓨터 간에 정보를 주고받기 위해 주소가 필요함
//	IPv4 :32bit
//	IPv6 :128bit

//	각 주소 체계는 호환이 안된다.
//	IPv6 : 보안 강화 / 전세계인이 사용 가능 / 일관된 헤더(효율적인 라우팅)
//	  / 서버 없이도 자체 IP 주소를 할당


}
