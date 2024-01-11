package testonjava.news;

/**
 * 
 * @author JIN
 * 셀레니움 예제를 위한 DTO 클래스
 *
 */
public class TestNewsDTO {
	
	private String thumbURI;
	private String naverNewsURI;
	private String originNewsURI;
	private String title;
	private String content;
	
	public String getThumbURI() {
		return thumbURI;
	}
	public void setThumbURI(String thumbURI) {
		this.thumbURI = thumbURI;
	}
	public String getNaverNewsURI() {
		return naverNewsURI;
	}
	public void setNaverNewsURI(String naverNewsURI) {
		this.naverNewsURI = naverNewsURI;
	}
	public String getOriginNewsURI() {
		return originNewsURI;
	}
	public void setOriginNewsURI(String originNewsURI) {
		this.originNewsURI = originNewsURI;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
		
	@Override
	public String toString() {
		return "TestNewsDTO [thumbURI=" + thumbURI + ", naverNewsURI=" + naverNewsURI + ", originNewsURI="
				+ originNewsURI + ", title=" + title + ", content=" + content + "]";
	}
}
