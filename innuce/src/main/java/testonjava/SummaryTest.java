package testonjava;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import org.json.JSONObject;

/**
 * 
 * @author JIN
 * summary 테스트
 *
 */
public class SummaryTest {

	public static void main(String[] args) throws IOException {
    	
		String os = System.getProperty("os.name").toLowerCase();
		String fileurl = "";
		
		if(os.contains("win")) {
			fileurl="c:/fullstack/naverinform.properties";
		} else {
			fileurl="/usr/workspace_innuce/naverinform.properties";
		}
		
		InputStream is = new FileInputStream(new File(fileurl));
    	Properties props = new Properties();
    	props.load(is);
    	props.get("naverNewsId");
		String result[] = new String[10];
		
		try {
			String apiURL = "https://naveropenapi.apigw.ntruss.com/text-summary/v1/summarize";
			
			String title[] = {
					"강기정 광주시장 \"한동훈, 5·18 정신 헌법 전문 수록 결단해야\""
			};
			
			String contents[] = {
					"강기정 광주시장이 4일 국민의힘 한동훈 비대위원장이 5·18 정신의 헌법 전문 수록에 찬성한다고 밝힌 데 대해 환영의 뜻을 밝히고 이번 총선에서 원포인트 개헌을 추진할 것을 촉구했다."
					+ "강 시장은 이날 SNS에 올린 글을 통해 \"광주를 찾은 한동훈 비대위원장이 5·18 정신의 헌법 전문 수록에 찬성한 데 대해 감사하다\"고 밝혔다."
					+ "이어 \"지금도 늦지 않았다\"면서 \"여야 의원(하태경·성일종·이학영·이병훈) 4명이 '5·18정신 헌법 전문 수록 추진본부'까지 구성한 상태\"라면서 \"이번 총선에서 원포인트 개헌을 이뤄내자\"고 촉구했다."
					+ "강 시장은 \"모두가 찬성한 상태다. 이제 한동훈 비대위원장의 한 걸음 더 나아간 결단을 요청드린다\"고 한 위원장의 결단을 요구했다."
					+ "한동훈 비대위원장은 이날 오전 광주 국립5·18민주묘지를 방문해 \"5월의 광주 정신은 어려운 상황에서 민주주의를 지키는 정신이다. 저는 대한민국의 헌법 정신과 그 정신이 정확히 일치한다고 생각한다\"며 \"헌법 전문에 5·18정신을 수록하는 것에 적극적으로 찬성한다\"고 말했다."
					+ "하지만 윤석열 대통령도 5.18 정신의 헌법 전문 수록을 대선 공약으로 제시했지만 진전이 없는 상황인 데다 한동훈 비대위원장도 헌법 전문 수록이 절차 문제로 쉽지 않다고 단서를 달아 개헌에 속도가 붙을지는 미지수라는 지적이다.",
			};
			
			long beforeTime = System.currentTimeMillis();
			
			URL url = new URL(apiURL);
			// post request
			/*
			 * { "document": { "title": "'하루 2000억' 판 커지는 간편송금 시장", "content":
			 * "간편송금 이용금액이 하루 평균 2000억원을 넘어섰다. 한국은행이 17일 발표한 '2019년 상반기중 전자지급서비스 이용 현황'에 따르면 올해 상반기 간편송금서비스 이용금액(일평균)은 지난해 하반기 대비 60.7% 증가한 2005억원으로 집계됐다. 같은 기간 이용건수(일평균)는 34.8% 늘어난 218만건이었다. 간편 송금 시장에는 선불전자지급서비스를 제공하는 전자금융업자와 금융기관 등이 참여하고 있다. 이용금액은 전자금융업자가 하루평균 1879억원, 금융기관이 126억원이었다. 한은은 카카오페이, 토스 등 간편송금 서비스를 제공하는 업체 간 경쟁이 심화되면서 이용규모가 크게 확대됐다고 분석했다. 국회 정무위원회 소속 바른미래당 유의동 의원에 따르면 카카오페이, 토스 등 선불전자지급서비스 제공업체는 지난해 마케팅 비용으로 1000억원 이상을 지출했다. 마케팅 비용 지출규모는 카카오페이가 491억원, 비바리퍼블리카(토스)가 134억원 등 순으로 많았다."
			 * }, "option": { "language": "ko", "model": "news", "tone": 2, "summaryCount":
			 * 3 } }
			 */
			for(int i = 0; i < title.length; i++) {
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("POST");
				con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", props.getProperty("naverClientID"));
				con.setRequestProperty("X-NCP-APIGW-API-KEY", props.getProperty("naverClientSecret"));
				con.setRequestProperty("Content-Type", "application/json");
				BufferedReader br = null;
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				JSONObject document = new JSONObject();
				document.put("title", "\""+title[i]+"\"");
				document.put("content", "\""+contents[i]+"\"");
	
				JSONObject option = new JSONObject();
				option.put("language", "ko");
				option.put("model", "news");
				option.put("tone", 2);
				option.put("summaryCount", 3);
	
				JSONObject total = new JSONObject();
				total.put("document", document);
				total.put("option", option);
				
				wr.write(total.toString().getBytes("utf-8"));

				int responseCode = con.getResponseCode();
				
				if (responseCode == 200) { // 정상 호출
					br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				} else { // 오류 발생
					System.out.println("error!!! responseCode = " + responseCode);
					br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				}
				String inputLine = "";
				StringBuffer response = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
					response.append(inputLine);
				}
				result[i] = response.toString();
				System.out.println(response.toString());
				wr.flush();
				wr.close();
				br.close();
			}
			
			long afterTime = System.currentTimeMillis();
			System.out.println("during time : " + (afterTime - beforeTime));
		} catch (Exception e) {
			System.out.println(e);
		}
		is.close();
	}

}
