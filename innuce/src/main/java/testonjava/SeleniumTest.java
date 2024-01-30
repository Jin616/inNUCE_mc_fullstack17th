package testonjava;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * 
 * @author JIN
 * 셀레니움 예제 수정 완
 * 
 */
public class SeleniumTest {
	
	static String url = "https://news.naver.com/main/main.naver?mode=LSD&mid=shm&sid1=100";
	
	public static void main(String[] args) throws InterruptedException {
		// selenium 4.6.0 버전부터 알아서 드라이버를 자동 설치 해줌 크롬드라이버 설치 불필요
		System.out.println("드라이버를 연결 중...");
		WebDriver driver = getDriver(url);
		System.out.println("드라이버 연결 성공!");
		
		// 요소들을 받아올 리스트 생성
		// By. 메소드를 이용해 원하는 값들을 검색해 가져올 수 있음. _cluster_content는 헤드라인에 있는 뉴스들
		WebElement sectionBody = driver.findElement(By.className("section_body"));
		List<WebElement> newsList = sectionBody.findElements(By.tagName("dt"));
		List<String> thumbUri = new ArrayList<>(); // use
		List<String> naverUri = new ArrayList<>();
		
		for(int i = 0; i < newsList.size(); i++) {
			if(i % 2 == 0) {
				driver.get("www.naver.com");
				driver.get(url);
//				Thread.sleep(2000);
				naverUri.add(newsList.get(i).findElement(By.tagName("a")).getAttribute("href"));
				thumbUri.add(newsList.get(i).findElement(By.tagName("img")).getAttribute("src"));
			}
		}
		
		for(String url : naverUri) {
			driver.get(url);
			
			WebElement content = driver.findElement(By.id("dic_area"));
			System.out.println(content.getText());
		}
		
		System.out.println("드라이버 종료 중...");
		driver.quit();
	}

	private static WebDriver getDriver(String url) {
		ChromeOptions chromeOptions = new ChromeOptions();
		
		chromeOptions.addArguments("--lang=ko");
		//chromeOptions.addArguments("--no-sandbox");
		chromeOptions.addArguments("--incognito"); // 시크릿모드로 열기
		chromeOptions.addArguments("--disable-extensions"); // 확장기능 비활성화
		chromeOptions.addArguments("--disable-dev-shm-usage"); // 공유 메모리 사용 비활성화
		chromeOptions.addArguments("--disable-gpu"); // gpu 사용 x
		//chromeOptions.addArguments("--start-maximized"); // 시작할때 최대사이즈로
		chromeOptions.addArguments("--disable-popup-blocking"); // 팝업 무시
		chromeOptions.addArguments("headless"); // 브라우저 화면 실행 x
		
		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.get(url);
		// 완전히 페이지가 로딩될때까지 대기
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		
		return driver;
	}
}
