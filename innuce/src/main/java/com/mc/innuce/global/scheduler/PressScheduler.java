package com.mc.innuce.global.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mc.innuce.domain.news.selenium.service.CrawlingPressService;
import com.mc.innuce.domain.news.service.PressService;

@Component
public class PressScheduler {
	
	@Autowired
	PressService ps;
	@Autowired
	CrawlingPressService cps;
	
	@Scheduled(cron = "0 0 0 * * *")
	public void scheduleUpdatePressDaily() {
		ps.updatePressTable(cps.getPressInform());
	}
	
}