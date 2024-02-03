package testonjava;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Test {
	public static void main(String[] args) {

		DateTimeFormatter dfLocalDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter dfUseDot = DateTimeFormatter.ofPattern("yyyy.MM.dd");

		System.out.println(dfUseDot.format(LocalDate.parse(LocalDate.now().toString(), dfLocalDate)));
		
		 String encodedString = "https://search.naver.com/search.naver?where=news&query=%EA%B1%B4%ED%9D%AC&sort=0&pd=3&ds=2022.01.13&de=2023.01.24&&mynews=1&office_type= 1&news_office_checked=1005";

	        try {
	            // Decode the URL-encoded string
	            String decodedString = URLDecoder.decode(encodedString, StandardCharsets.UTF_8.toString());

	            // Print the decoded string
	            System.out.println("Decoded String: " + decodedString);
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        }
	}
}
