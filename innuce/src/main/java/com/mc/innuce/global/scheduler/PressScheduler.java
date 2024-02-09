package com.mc.innuce.global.scheduler;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mc.innuce.domain.news.selenium.service.CrawlingPressService;
import com.mc.innuce.domain.news.service.PressService;
import com.mc.innuce.global.config.Config;

@Component
public class PressScheduler {

	@Autowired
	PressService ps;
	@Autowired
	CrawlingPressService cps;

	private LocalDate updatePressLocalDate = LocalDate.now();
	
	@Scheduled(cron = "0 0 * * * *")
	public void scheduleUpdatePressDaily() {
		
		// if(updatePressLocalDate.isBefore(LocalDate.now())) {
		if (Config.CURRENT_OS.equals("linux") && updatePressLocalDate.isBefore(LocalDate.now())) {
			ps.updatePressTable(cps.getPressInform());
			
			updatePressLocalDate = LocalDate.now();
		}
	}

}