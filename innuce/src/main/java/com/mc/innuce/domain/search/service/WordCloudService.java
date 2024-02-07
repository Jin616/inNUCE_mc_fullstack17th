package com.mc.innuce.domain.search.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.search.dao.KeywordDAO;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;

@Service
public class WordCloudService {
	@Autowired
	KeywordDAO dao;

	Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
	
	public Map<String,HashMap<String,Integer>> map = new HashMap<>();
	
	
	public void parsingData(String number) {

		String[] sid = { "정치", "경제", "사회", "생활/문화", "세계", "IT/과학" };

		
		String path = System.getProperty("user.dir");
//		System.out.println(path);
//		D:\fullstack\workspace_innuce\innuce
//		사전 경로 
		komoran.setFWDic(path + "/src/main/resources/static/dictionary/fwd.user");
		komoran.setUserDic(path + "/src/main/resources/static/dictionary/dic.user");

//		String dataSource = new NaverCrawler().crawler(number);

//		System.out.println(number);
		List<String> dataSource = new ArrayList<>();
		Map<String, Object> dataMap = new HashMap<>();

		List<Map<String, Object>> dataList = new ArrayList<>();

		Map<String, Object> resultMap = new HashMap<>();
		String eachDescription = "";
//	{"result",[{"description":content.getText()},
//	{"description":content.getText()},
//	{"description":content.getText()},
//	{"description":content.getText()} ...]}
		
		dataSource = dao.getCategoryContent(sid[Integer.parseInt(number)]);
		for (String s : dataSource) {
			dataMap.put("description", s);
			System.out.println("description : "+dataMap.get("description"));
			eachDescription += s;
		}
		dataList.add(dataMap);
		resultMap.put("result", dataList);

	

//		List<Map<String, Object>> items = (List<Map<String, Object>>) resultMap.get("result");
//		
//		for (Map<String, Object> item : items) {
//			eachDescription += item.get("description");
//		}

//		System.out.println(dataSource);
//		입력 텍스트에 대한 형태소 분석을 수행
		KomoranResult komoranResult = komoran.analyze(eachDescription);
//		System.out.println("ParsingKomoran(parsingData): "+komoranResult.getNouns());
//		고유명사 , 일반명사 , 의존명사 
//		="NNP", "NNG", "NNB"
		List<String> analyzeList = komoranResult.getMorphesByTags("NNP", "NNG", "NNB");

		HashMap<String, Integer> listHash = new HashMap<>();

		for (String token : analyzeList) {
//			analyzeList에서 문자열 token의 빈도를 계산합니다. 
			if (token.length() >= 2) {
				int num = Collections.frequency(analyzeList, token);
				if (num >= 3) {
					listHash.put(token, num);
				}
			}
		}
//		System.out.println("listHash" + listHash);
		map.put(number, listHash);
//		System.out.println(map.get(number));
//		System.out.println(map.size());
	}

	public HashMap<String, Integer> getCategoryContent(String category) {

		if(map.isEmpty()) {
			initCloud();
		}
//	System.out.println("getCategoryContent"+" "+map.get(category));	
		return map.get(category);
	}

	public void initCloud() {
		String[] strArr = {"0","1","2","3","4","5"};
		
		for (String string : strArr) {
			parsingData(string);
		}
	}

}
