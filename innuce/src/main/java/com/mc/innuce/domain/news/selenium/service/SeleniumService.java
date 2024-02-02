package com.mc.innuce.domain.news.selenium.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.news.service.NewsService;
import com.mc.innuce.domain.news.service.PressService;
import com.mc.innuce.domain.search.dto.KeywordDTO;
import com.mc.innuce.domain.search.service.CRUDService;

@Service
public class SeleniumService {

	@Autowired
	CrawlingNewsService cns;
	@Autowired
	CrawlingPressService cps;
	@Autowired
	NewsService ns;
	@Autowired
	PressService ps;
	@Autowired
	CRUDService cruds;

	Map<String, HashSet<String>> searchingKeywordMap = new HashMap<>();

	// 삭제 예정
	public void searchBackToday(String keyword, LocalDate date) {
		
	}

	// 수정 예정
	public void searchBack(String keyword, String ds, String de) {

		// keyworddto 생성 및 불러오기
		KeywordDTO dto = cruds.oneKeyword(keyword);

		if (dto == null) {
			cruds.insertKeyword(keyword);
			dto = cruds.oneKeyword(keyword);
		}

		// recentDate 설정
		LocalDate recentDate = dto.getKeyword_recent_time().toLocalDateTime().toLocalDate();

		// ds, de 파라미터 빈 값 조정
		if (ds.isEmpty())
			ds = LocalDate.now().toString();

		if (de.isEmpty())
			de = LocalDate.now().toString();

		// localdate로 파싱 및 오류 잡아줌
		try {
			LocalDate startDate = LocalDate.parse(ds);
			LocalDate endDate = LocalDate.parse(de);

			if (startDate.isAfter(endDate))
				startDate = endDate;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("날짜 양식 오류\n ds : " + ds + ", de : " + de);
			System.out.println("양식 -> yyyy-mm-dd");
		}

		/*
		 * 싱글톤으로 관리되는 이 서비스 객체에 Map(같은 키워드)과 Set(여러 날짜)으로 이중으로 크롤러가 돌지 않기 위한 코드 1. 맵에
		 * 키워드가 있는지 여부를 확인. 없다면 추가 2. 해당 키워드에 해당하는 hashSet에 string으로 변환된 localDate값이
		 * 있는지를 조회, 있다면 추가 후 검색할 arrayList에 담음
		 */
		if (!searchingKeywordMap.containsKey(keyword))
			searchingKeywordMap.put(keyword, new HashSet<String>());

		String today = LocalDate.now().toString();
		Set<String> dateSet = searchingKeywordMap.get(keyword);
		if (!dateSet.contains(today)) {
			dateSet.add(today);
			System.out.println(keyword);
			System.out.println("date 추가 완료" + today);
			// ns.insertNewsList(cns.getSearchNews(keyword));
			dateSet.remove(today);
			System.out.println("date 제거 완료");
		}

		if (dateSet.size() == 0) {
			System.out.println("map에서 해당 " + keyword + "를 제거합니다.");
			searchingKeywordMap.remove(keyword);
		}
	}

	public String search(String keyword, String sort, String pressOpt, String press, String periodOpt, String ds,
			String de) {
		String url = "https://search.naver.com/search.naver?where=news";

		keyword = "&query=" + keyword;
		sort = "&sort=" + sort;
		periodOpt = "&pd=" + periodOpt;

		DateTimeFormatter dfLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter dfUseDot = DateTimeFormatter.ofPattern("yyyy.MM.dd");

		if (ds.isEmpty())
			ds = LocalDate.now().toString();
		if (de.isEmpty())
			de = LocalDate.now().toString();

		String period = "&ds=" + dfUseDot.format(LocalDate.parse(ds, dfLocalDate)) + "&de="
				+ dfUseDot.format(LocalDate.parse(de, dfLocalDate));

		pressOpt = "&&mynews=1&office_type=" + pressOpt;
		press = "&news_office_checked=1"
				+ (press.length() == 3 ? press : press.length() == 2 ? "0" + press : "00" + press);

		url += keyword + sort + periodOpt + period + pressOpt + press;
		return url;
	}

}
