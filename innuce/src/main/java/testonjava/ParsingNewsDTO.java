package testonjava;

import com.mc.innuce.global.util.hreftonewsdto.WebConverter;

public class ParsingNewsDTO {
	// https://n.news.naver.com/mnews/article/052/0001988676?sid=100
	public static void main(String[] args) {
		WebConverter wc = new WebConverter();

		System.out.println("presskey");
		System.out.println(wc.getStringPressKey("https://n.news.naver.com/mnews/article/052/0001988676?sid=100"));

		System.out.println("newskey");
		System.out.println(wc.getLongNewsKey("https://n.news.naver.com/mnews/article/052/0001988676?sid=100"));
	}

}
