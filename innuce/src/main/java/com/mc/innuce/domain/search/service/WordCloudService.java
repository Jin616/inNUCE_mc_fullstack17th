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
import com.mc.innuce.global.util.komoran.KomoranModel;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;

@Service
public class WordCloudService {
//	private Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
	
	@Autowired
	KeywordDAO dao;

	public Map<String, HashMap<String, Integer>> map = new HashMap<>();

	public void parsingData(String number) {

		String[] sid = { "정치", "경제", "사회", "생활/문화", "세계", "IT/과학" };

//		String path = System.getProperty("user.dir");
//		System.out.println(path);
//		D:\fullstack\workspace_innuce\innuce
//		사전 경로 
//		komoran.setFWDic(path + "/src/main/resources/static/dictionary/fwd.user");
//		komoran.setUserDic(path + "/src/main/resources/static/dictionary/dic.user");

//		String dataSource = new NaverCrawler().crawler(number);

//		System.out.println(number);
		List<String> dataSource = dao.getCategoryContent(sid[Integer.parseInt(number)]);
//		System.out.println(number+" "+dataSource.size());
//		System.out.println("sql 실행 결과 : " + dataSource);
		Map<String, Object> dataMap = new HashMap<>();

		List<Map<String, Object>> dataList = new ArrayList<>();

		Map<String, Object> resultMap = new HashMap<>();
//	{"result",[{"description":content.getText()},
//	{"description":content.getText()},
//	{"description":content.getText()},
//	{"description":content.getText()} ...]}
		for (String s : dataSource) {
			dataMap.put("description", s);
		}
		dataList.add(dataMap);
		resultMap.put("result", dataList);

		String eachDescription = "";

		List<Map<String, Object>> items = (List<Map<String, Object>>) resultMap.get("result");
		for (Map<String, Object> item : items) {
			eachDescription += item.get("description");
		}

//		입력 텍스트에 대한 형태소 분석을 수행
		KomoranResult komoranResult = KomoranModel.getInstance().getKomoran().analyze(eachDescription);
//		System.out.println("ParsingKomoran(parsingData): "+komoranResult.getNouns());
//		고유명사 , 일반명사 , 의존명사 
//		="NNP", "NNG", "NNB"
		List<String> analyzeList = komoranResult.getMorphesByTags("NNP", "NNG", "NNB");

		HashMap<String, Integer> listHash = new HashMap<>();

		for (String token : analyzeList) {
//			analyzeList에서 문자열 token의 빈도를 계산합니다. 
			if (token.length() >= 2) {
				int num = Collections.frequency(analyzeList, token);
				if (num >= 2) {
					listHash.put(token, num);
				}
			}
//			if(listHash.size())
			
		}
		map.put(number, listHash);
	}

	public HashMap<String, Integer> getCategoryContent(String category) {

		if (map.isEmpty()) {
			initCloud();
		}
		return map.get(category);
	}

	public void initCloud() {
		String[] strArr = { "0", "1", "2", "3", "4", "5" };

		for (String string : strArr) {
			parsingData(string);
		}
	}

}
