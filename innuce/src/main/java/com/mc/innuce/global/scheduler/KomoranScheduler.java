package com.mc.innuce.global.scheduler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mc.innuce.domain.search.service.WordCloudService;

@Component
public class KomoranScheduler {
	@Autowired
	WordCloudService service;
	

	@Scheduled(cron = "0 0 * * * *") // 정각부터 1시간 마다
	public void scheduleUpdateWordCloud() {
		service.initCloud();
	}

}