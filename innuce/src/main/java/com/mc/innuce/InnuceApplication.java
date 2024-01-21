package com.mc.innuce;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.mc.innuce.domain.news.dto.PressDTO;
import com.mc.innuce.domain.news.selenium.service.CrawlingNewsService;
import com.mc.innuce.domain.news.selenium.service.CrawlingPressService;
import com.mc.innuce.domain.news.service.NewsService;
import com.mc.innuce.domain.news.service.PressService;


@SpringBootApplication
public class InnuceApplication {
	
	static PressService ps;
	static NewsService ns;
	static CrawlingPressService cps;
	static CrawlingNewsService cns;
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(InnuceApplication.class, args);
        
		ps = context.getBean(PressService.class);
		cps = context.getBean(CrawlingPressService.class);
		ns = context.getBean(NewsService.class);
		cns = context.getBean(CrawlingNewsService.class);
		
		//testPressServiceWithCopsole();
		testNewsServiceWithCopsole();
	}

	private static void testNewsServiceWithCopsole() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			String command = sc.nextLine();
			if(command.equals("countnews")) {
				System.out.println(ns.countAllNews());
			} else if(command.equals("shownewskeylist")) {
				List<Long> keyList = ns.getAllNewsListOnlyKey();
				if(keyList.size() == 0)
					System.out.println("뉴스 데이터가 없습니다.");
				else
					for(long key : keyList)
						System.out.println(key);
			} else if(command.equals("getnewslist")) {
				ns.insertNewsList(cns.getCategoryNews());
			}
		}
		
	}

	private static void testPressServiceWithCopsole() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			String command = sc.nextLine();
			if(command.equals("showpresslist")) {
				List<PressDTO> list = ps.getAllPressList();
				if(list.size() == 0)
					System.out.println("보관된 언론사가 없습니다.");
				else
					for(PressDTO dto : list)
						System.out.println(dto);
			} else if(command.equals("countpress")) {
				List<PressDTO> list = ps.getAllPressList();
				if(list.size() == 0)
					System.out.println("보관된 언론사가 없습니다.");
				else
					System.out.println(ps.getAllPressList().size());
			} else if(command.contains("updatepress")) {
				if(command.contains("force")) {
					System.out.println("강제로 'press table'을 오늘 날짜로 업데이트합니다.");
					ps.updatePressUpdateTime();
					ps.updatePressTable(cps.getPressInform());
				} else {
					Calendar updatetime = ps.getPressUpdateTime();
					Calendar now = Calendar.getInstance();
										
					if(updatetime.DATE == now.DATE) {
						System.out.println("'press table'은 오늘 이미 업데이트 되었습니다.");
					} else {
						System.out.println("'press table'을 오늘 날짜로 업데이트합니다.");
						ps.updatePressUpdateTime();
						ps.updatePressTable(cps.getPressInform());
					}
				}
			}
			
			if(command.equals("quit")) {
				if(sc != null) sc.close();
				break;
			}
			
		}
	}
	
}
