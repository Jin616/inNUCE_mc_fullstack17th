package com.mc.innuce.domain.search.wordcloud;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;

@Component
public class ParsingKomoran {
	public HashMap<String, Integer> parsingData(String number) {

		Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
		String path = System.getProperty("user.dir");
//		System.out.println(path);
//		D:\fullstack\workspace_innuce\innuce
//		사전 경로 
		komoran.setFWDic(path + "/src/main/resources/static/dictionary/fwd.user");
		komoran.setUserDic(path + "/src/main/resources/static/dictionary/dic.user");

		String dataSource = new NaverCrawler().crawler(number);
//		System.out.println(dataSource);
//		입력 텍스트에 대한 형태소 분석을 수행
		KomoranResult komoranResult = komoran.analyze(dataSource);
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
		}

		return listHash;
	}
}
