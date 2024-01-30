package com.mc.innuce.domain.news.naverapi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Stream;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 * 
 * @author JIN
 *
 */
@Service
public class SummaryService {
	
	private String clientId;
	private String clientSecret;
	
	public SummaryService() {
		
		try {
			// properties 파일 naver 키들 담겨있음 서버 올릴때 수정 필수 !
			InputStream is = new FileInputStream(new File("C:/fullstack/naverinform.properties"));
			Properties props = new Properties();
			props.load(is);			
			this.clientId = props.getProperty("naverClientID");
			this.clientSecret = props.getProperty("naverClientSecret");
		} catch (FileNotFoundException e) {
			System.out.println("naverinform file이 없습니다.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("SummaryService ERROR!");
			e.printStackTrace();
		}
		
	}
	
	public String summary(String title, String content) {
		return summary(title, content, 3);
	}
	
	public String summary(String title, String content, int count) {
		String result = "";
		// title, content [] 제거
		title = removeBracketsAndString(title);
		content = removeBracketsAndString(content);
		int contentLen = content.length();
		int titleLen = title.length();
		if(contentLen < 250)
			return content;
		
		String[] text = new String[contentLen / (1600 - titleLen) + 1];
		String[] contents = splitSentences(content);
		
		for(int i = 0, j = 0; i < text.length; i++) {
			text[i] = "";
			for(; j < contents.length; j++) {
				text[i] += contents[j];
				if(text[i].length() > (1600 - titleLen) / text.length) {
					j++;
					break;
				}
			}
		}
		
		try {
			for(int i = 0; i < text.length; i++) {
				URL url = new URL("https://naveropenapi.apigw.ntruss.com/text-summary/v1/summarize");
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("POST");
				con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
				con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
				con.setRequestProperty("Content-Type", "application/json");

				con.setDoOutput(true);
				
				if(text[i].length() < 100)
					break;
				
				JSONObject document = new JSONObject();
				document.put("title", title);
				document.put("content", text[i]);
	
				JSONObject option = new JSONObject();
				option.put("language", "ko");
				option.put("model", "news");
				option.put("tone", 3);
				option.put("summaryCount", count);
	
				JSONObject total = new JSONObject();
				total.put("document", document);
				total.put("option", option);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.write(total.toString().getBytes("utf-8"));
				wr.flush();
				wr.close();
				
				int responseCode = con.getResponseCode();
				BufferedReader br;
				if (responseCode == 200) { // 정상 호출
					br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else { // 오류 발생
					System.out.println("error!!! responseCode = " + responseCode);
					System.out.println(text[i]);
					br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}
				
				String inputLine;
				StringBuffer response = new StringBuffer();
				
				while ((inputLine = br.readLine()) != null)
					response.append(inputLine);
				
				br.close();
				
				if(responseCode == 200)
					result += (String)new JSONObject(response.toString()).get("summary") + "\n";
				else {
					System.out.println(response.toString());
					result += "원문참조";
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		if(result.equals("")) {
			return content;
		}
		return result;
	}

	private String[] splitSentences(String content) {
		String[] before = content.split("\\.");
		ArrayList<String> list = new ArrayList<>();
		
		content = content.trim();
		for(String s : before) {
			s = s.replaceAll("\\\n", "");
			s = s.strip();
			if(!s.isEmpty())
				list.add(s + ".");
		}
		
		return list.toArray(new String[0]);
	}

	private String removeBracketsAndString(String text) {
		text = text.strip();
		if(text.contains("기자 = "))
			text = text.substring(text.indexOf("기자 = ") + 4);
		
		return text;
	}
}
