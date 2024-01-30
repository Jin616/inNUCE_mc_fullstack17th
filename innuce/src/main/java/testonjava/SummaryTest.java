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
    	
    	InputStream is = new FileInputStream(new File("C:/fullstack/naverinform.properties"));
    	Properties props = new Properties();
    	props.load(is);
    	props.get("naverNewsId");
		String result[] = new String[10];
		
		try {
			String apiURL = "https://naveropenapi.apigw.ntruss.com/text-summary/v1/summarize";
			
			String title[] = {
					"北 ‘핵잠’ 3년간 진도 못내다가… 러와 밀착후 속도전"
			};
			
			String contents[] = {
					"■ 방송 : YTN 라디오 FM 94.5 (13:00~14:00)\r\n"
					+ "■ 진행 : 이승훈 앵커\r\n"
					+ "■ 방송일 : 2024년 1월 29일 (월요일)\r\n"
					+ "■ 대담 : 김영민 아나운서\r\n"
					+ "* 아래 텍스트는 실제 방송 내용과 차이가 있을 수 있으니 보다 정확한 내용은 방송으로 확인하시기 바랍니다.\r\n"
					+ "\r\n"
					+ "[이앤피] 이낙연·비명계 '개혁미래당' 창당‥ 양향자 \"빅텐트는 어려울 듯\"外\r\n"
					+ "◇ 이승훈 앵커(이하 이승훈) : 점심 먹고 아아 한 잔 하면서 듣는 오늘의 영민한 주요뉴스. '영민한 뉴스, 아아!' 시간입니다.\r\n"
					+ "영특하고 민첩한 뉴스캐스터, 김영민 아나운서와 함께합니다. 어서 오세요.\r\n"
					+ "\r\n"
					+ "◆ 김영민 아나운서(이하 김영민) : 네 안녕하세요.\r\n"
					+ "\r\n"
					+ "◇ 이승훈 : 첫 번째 소식입니다. 이낙연 전 대표가 이끄는 '새로운 미래'와 더불어민주당 탈당파 의원들의 '미래대연합'이 단일 정당으로 통합하죠?\r\n"
					+ "\r\n"
					+ "◆ 김영민 : 더불어민주당 탈당 의원들이 주축이 된 신당 추진 세력인 '미래대연합'과 이낙연 전 민주당 대표가 이끄는 '새로운미래'가 단일 정당으로 통합합니다. 어제 이들은 공동 창당에 합의하고 다음 달 4일 가칭 '개혁미래당'이란 이름으로 중앙당 창당대회를 열 예정이라고 발표했는데요. 당명을 개혁미래당으로 정한 이유는 정치개혁, 사회개혁, 민생 개혁 등 개혁을 선도하고 미래로 나아가겠다는 의미라고 설명했습니다. 현재 보수 진영에서는 '개혁신당', 진보 진영에서는 '개혁미래당', 이렇게 두 개의 텐트가 꾸려진 모양새인데요. 이들은 현재의 '중텐트'를 넘어 개혁신당 이준석 대표의 신당까지 끌어들여 '빅텐트'를 꾸리겠다는 입장입니다.\r\n"
					+ "\r\n"
					+ "◇ 이승훈 : 이준석 개혁신당 대표는 이들의 당명을 비판했죠?\r\n"
					+ "\r\n"
					+ "◆ 김영민 : 이준석 개혁신당 대표는 어제 자신의 소셜미디어에 \"생각이 비슷한 분들끼리 모이는 것은 언제나 축하한다\"면서도 당명에 대해 \"옆에 신장개업한 중국집 이름 조금 알려져 간다고 그대로 차용하겠다는 것\"이라고 비판했습니다. 이 대표는 \"개혁신당이 출범해 개혁을 화두로 삼아 이슈를 만들어가는 상황에서 '미래대연합'과 '새로운미래'가 합쳐져서 '개혁미래당'이라는 당명을 쓰겠다는 것은 의도가 명백해 보인다\"고 주장했습니다. 그러면서 \"무임승차는 지하철이든 당명이든 곤란하다\"는 지적도 했습니다. 이 발언은 앞서 이 대표가 65세 이상 노인의 지하철 무임승차 혜택을 없애야 한다는 공약을 발표한 데 따른 것으로 보입니다.\r\n"
					+ "\r\n"
					+ "◇ 이승훈 : 양향자 대표는 개혁미래당과의 합당이 어려울 거라고 내다봤네요.\r\n"
					+ "\r\n"
					+ "◆ 김영민 : 이준석 대표의 개혁신당과 합당을 선언한 한국의 희망 양향자 대표는 오늘 라디오 방송에 출연해 \"정치공학적 세력 규합 또 합종연횡·이합집산 이것으로는 국민들께 희망을 드릴 수가 없다\"며 \"(개혁미래당 측이) 어떤 비전을 가지고 있는지 잘 모르겠다\"며 회의적인 입장을 전했습니다. 양 대표는 개혁미래당과의 합당 가능성에 대해서는 \"국민들께서 지금 양당의 극단의 정치에 균열을 내달라는 그런 명령이지 않나 근데 그 균열을 빅텐트로 낼 수 있다고 생각하지 않다\"고 밝혔습니다. 총선 일정상으로도 어렵다고 전망했는데요. \"(이준석 대표의 개혁신당과) 31일 드디어 추인 절차를 거쳐서 합당이 된다. 근데 그 과정에 너무나 그런 과정을 또 거치기에는 시간적으로·물리적으로 어려워 보인다는 게 저희의 생각\"이라고 덧붙였습니다.\r\n"
					+ "\r\n"
					+ "◇ 이승훈 : 다음 소식입니다. 국민의힘을 탈당하고 이준석 대표 신당에 합류할 가능성이 제기됐던 유승민 전 의원이 당에 남기로 했네요.\r\n"
					+ "\r\n"
					
					
					
					
					
					
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
