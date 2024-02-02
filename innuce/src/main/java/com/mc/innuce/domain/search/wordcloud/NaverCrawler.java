package com.mc.innuce.domain.search.wordcloud;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Component
public class NaverCrawler { // 베이스 URL
//	JSON 문자열 -> JSON(가공) -> JSON 문자열
	public String crawler(String num) {
		String crawlerString = "";
		try {

//			String response = naverSearch.getNewsJson(word);
//			System.out.println(response);

			// response를 정리된 JSON으로 만들기
//			String[] fields = {"title","description"};
			// {"result": itemList}

			Map<String, Object> result = new SeleniumTest().descriptionToMap(num);
//			System.out.println("정리된 JSON:"+result);
//			if(result.size()>0) {
//				System.out.println("total => "+result.get("total"));
//			}
			// Json문자열 <- Map
			List<Map<String, Object>> items = (List<Map<String, Object>>) result.get("result");
			for (Map<String, Object> item : items) {
				crawlerString += item.get("description");
//				crawlerString += item.get("title");
//				System.out.println("crawlerString: "+crawlerString);
			}
//			System.out.println("crawlerString: "+crawlerString);
			return crawlerString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

//Json객체 -> JSON -> Map
//	JSON문자열을 정리하고 특정 필드를 추출하여 Map으로 만든 후 반환
	public Map<String, Object> jsonToMap(String response, String[] fields) {
		// {"result": itemList}
		Map<String, Object> mapData = new HashMap<>();

		try {
			JSONParser parser = new JSONParser();
			// JSON 문자열을 다루기 편하게 JSONObject로 변환
			JSONObject result = (JSONObject) parser.parse(response);
			// {"title": Object}
//			mapData.put("total",result.get("total"));
//			content라는 필드의 키의 값들을 JSONArray로 
			JSONArray items = (JSONArray) result.get("items");

			List<Map<String, Object>> itemList = new ArrayList<>();

//			content라는 키의 값들의 갯수를 기준으로 for
			for (int i = 0; i < items.size(); i++) {
//				값들을 하나하나 가져옴
				JSONObject item = (JSONObject) items.get(i);
//				모든 item에는 title,description이라는 키가 존재함
				Map<String, Object> itemMap = new HashMap<>();

//				String[] fields = {"title","description"};
				for (String field : fields) {
//					{title을 키 : item에서 field를 키로 가지는 값}
					itemMap.put(field, item.get(field));
				}
				System.out.println(i + ":" + itemMap);
//				[item1(title,description)
//				,item2(title,description)
//				,item3(title,description)
//				,item4(title,description)]
				itemList.add(itemMap);
			}
			System.out.println("itemList:" + itemList);
			mapData.put("result", itemList);

		} catch (Exception e) {
			System.out.println("getResult error -> " + "파싱 실패, " + e.getMessage());
		}

		return mapData;
	}

}
