package com.mc.innuce.domain.search.geoloaction;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;


public class GeolocationMain {

	public static void main(String[] args) throws Exception{

		try {
//			url -> ip주소
//			InetAddress inet = InetAddress.getByName("www.naver.com");
//			String ip = inet.getHostAddress();
			
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			String ip = request.getRemoteAddr();
			System.out.println("ip주소 : "+ip);
			
//			121.186.56.92
			
			String subURL = "/geolocation/v2/geoLocation?ip="+"121.186.56.92"+"&ext=t&responseFormatType=json";

			String timeStamp = String.valueOf(System.currentTimeMillis());
			String accessKey = NaverInfo.geo_Access_Key_ID;
			String secretKey = NaverInfo.geo_Secret_Key;

			String signKey=makeSignature(subURL,timeStamp,accessKey,secretKey);
			
			String apiURL = "https://geolocation.apigw.ntruss.com"+subURL;
			URL url = new URL(apiURL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("x-ncp-apigw-timestamp", timeStamp);
			conn.setRequestProperty("x-ncp-iam-access-key", accessKey);
			//secretKey 암호화하기
			conn.setRequestProperty("x-ncp-apigw-signature-v2", signKey);
			
			conn.setDoOutput(true);

		
			BufferedReader br = null;
			int responseCode = conn.getResponseCode();
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else { // 오류 발생
				System.out.println("error!!!!!!! responseCode= " + responseCode);
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			String inputLine;

			if (br != null) {
				StringBuffer response = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
					response.append(inputLine);
				}
				br.close();
				System.out.println(response.toString());
			} else {
				System.out.println("error !!!");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//secretKey 암호화하기
	public static String makeSignature(String subURL , String timeStamp, String accessKey, String secretKey) throws Exception {
		
		String space = " ";					// one space
		String newLine = "\n";					// new line
		String method = "GET";					// method
//		String url = "/photos/puppy.jpg?query1=&query2";	// https://localhost:9070+url
//		String timestamp = "{timestamp}";			// current timestamp (epoch)
//		String accessKey = "{accessKey}";			// access key id (from portal or Sub Account)
//		String secretKey = "{secretKey}";

		String message = new StringBuilder()
			.append(method)
			.append(space)
			.append(subURL)
			.append(newLine)
			.append(timeStamp)
			.append(newLine)
			.append(accessKey)
			.toString();

		SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(signingKey);

		byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
		String encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);

	  return encodeBase64String;
	}
}
