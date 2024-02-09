package com.mc.innuce;

import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mc.innuce.domain.news.dto.NewsDTO;
import com.mc.innuce.domain.news.service.NewsService;
import com.mc.innuce.domain.user.dto.UserDTO;
import com.mc.innuce.domain.user.service.ScrapService;
import com.mc.innuce.domain.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ScrapController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ScrapService scrapService;
	
	@Autowired
	NewsService newsService;
	
	// 스크랩 리스트 불러오기
	@PostMapping("/getscraplist")
	@ResponseBody
	public List<Long> getscraplist(String user_id) {
			
		UserDTO userDto = userService.selectOneUser(user_id);
		int user_key = userDto.getUser_key();
		
		List<NewsDTO> fullScrapList;
		fullScrapList = scrapService.getMyAllScrap(user_key);
		
		List<Long> newsKeyScrapList = new ArrayList<>();
		
		// NewsDTO가 담긴 fullScrapList에서 news_key만 뽑기
		for (int i=0; i< fullScrapList.size();i++) {
			newsKeyScrapList.add(fullScrapList.get(i).getNews_key());
		}
	
		return newsKeyScrapList;
		}
	// 스크랩 기능
	@PostMapping("/scrapnews")
	@ResponseBody
	public void scrapnews(String user_id, long news_key) {
		
		UserDTO userDto = userService.selectOneUser(user_id);
		int user_key = userDto.getUser_key();

		//NewsDTO newsDto = newsService.selectOne(news_key);
		
		scrapService.scrapNews(user_key,news_key);
		System.out.println("컨트롤러통과");
		
	}
	
	// 스크랩 취소 기능
	@PostMapping("/scrapnewscancel")
	@ResponseBody
	public void scrapnewscancel(String user_id, long news_key) {
		
		UserDTO userDto = userService.selectOneUser(user_id);
		int user_key = userDto.getUser_key();

		//NewsDTO newsDto = newsService.selectOne(news_key);
			
		scrapService.cancelScrapNews(user_key,news_key);
		System.out.println("컨트롤러통과");
			
	}
	
	// 마이페이지에서 스크랩한 기사 보기
	@GetMapping("/mypageScrap")
	public ModelAndView mypage_scarp(HttpSession session
			,@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pagenum,
			@RequestParam(value = "search_title", required = false, defaultValue = "") String news_title,
			@RequestParam(value = "search_content", required = false, defaultValue = "") String news_content) {
		
		ModelAndView mv = new ModelAndView();

		System.out.println("컨트롤러입장");
		UserDTO userdto = (UserDTO) session.getAttribute("login_user");
		int user_key = userdto.getUser_key();
		System.out.println("유저키"+user_key);
		// 내가 한 스크랩 수
		int total_scrap;
		// 마이페이지 내에서 기사제목 및 내용 검색안한경우
		if(news_title.length() == 0 && news_content.length() == 0) {
			total_scrap = scrapService.countMyTotalScrap(user_key);
		}
		// 뉴스 제목으로 검색한경우
		else if(news_title.length() != 0 && news_content.length() == 0) {
			total_scrap = scrapService.countMyTotalScrapByTitle(user_key,news_title);
		}
		// 뉴스 내용으로 검색한경우
		else {
			total_scrap = scrapService.countMyTotalScrapByContent(user_key,news_content);
		}
		
		// 뉴스DTO들 담아오기
		List<NewsDTO> fullScrapList ;
		// 마이페이지 내에서 기사제목 및 내용 검색안한경우
		if(news_title.length() == 0 && news_content.length() == 0) {
			fullScrapList = scrapService.getMyAllScrap(user_key);
		}
		// 마이페이지 내에서 기사 제목으로 검색한경우
		else if(news_title.length() != 0 && news_content.length() == 0) {
			fullScrapList = scrapService.getMyAllScrapByTitle(user_key,news_title);
		}
		
		// 마이페이지 내에서 기사 내용으로 검색한경우
		else {
			fullScrapList = scrapService.getMyAllScrapByContent(user_key,news_content);
		}
		
		// 다중 쿼리에서 limit 이 안돼서 컨트롤러 단에서 페이징 해줄예정
		// 지금 fullScrapList에는 모든 스크랩한 newsDTO가 담겨있음
		// 이걸 한페이지에서 보여줄 scrap_in_page에 맞게 잘라서 보내줄예정
		
		// 한페이지에 보여줄 스크랩 수
		int scrap_in_page=5;
		// 마지막 페이지에 보여줄 user 수
		int scrap_in_last_page;
		// 총 페이지 숫자
		int page_count;
		if (total_scrap % scrap_in_page == 0) {
			scrap_in_last_page = scrap_in_page;
			page_count = total_scrap / scrap_in_page;
			
		} else {
			scrap_in_last_page = total_scrap % scrap_in_page;
			page_count = total_scrap / scrap_in_page +1;
		}
		// 총 페이지 숫자
		
		System.out.println(pagenum);
		System.out.println(page_count);
		System.out.println(pagenum==page_count);
		System.out.println(fullScrapList.size());
		// 우리가 현재가진 scrapList에서 인덱싱으로 시작할 지점	
		int starting_point = (pagenum - 1) * scrap_in_page;
		
		//model에 실어 보낼 페이징된 scrapList만들기
		List<NewsDTO> scrapList = new ArrayList<NewsDTO>() ;
		// 마지막 페이지라서 scrap_in_page 만큼 반복하면 안되는 경우 
		if(pagenum == page_count) {
			//starting_point에서 시작해서 fullScrapList 갯수까지 반복해야함
			for(int i=starting_point; i < fullScrapList.size();i++) {
				
				scrapList.add(fullScrapList.get(i));
			}
		}	
		//정상적으로 전체 페이지를 채우는경우
		else {
			for(int i=starting_point; i < starting_point+scrap_in_page ;i++) {
				scrapList.add(fullScrapList.get(i));	
			}
		};
		mv.addObject("search_title",news_title);
		mv.addObject("search_content",news_content);
		mv.addObject("scrap_in_page",scrap_in_page);
		mv.addObject("scrapList",scrapList);
		mv.addObject("totalCount", total_scrap);
		mv.addObject("pageCount",page_count);
		mv.setViewName("user/mypageScrap");
		
		return mv;
	}
}
