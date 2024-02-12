package com.mc.innuce.domain.news.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mc.innuce.domain.news.dto.PressDTO;
import com.mc.innuce.domain.news.service.PressService;
import com.mc.innuce.global.util.jsonparsefromdto.JSONParser;

@RestController
public class PressController {

	@Autowired
	PressService ps;
	
	private List<PressDTO> pressList = new ArrayList<>();
	private String pressJSON = "";
	
	@GetMapping("/pressinfo")
	@ResponseBody
	public String pressInfo() {
		if(pressList.isEmpty()) pressList = ps.getAllPressList();
		if(pressJSON.isEmpty()) pressJSON = new JSONParser().getJsonArrayPress(pressList).toString();
		
		return pressJSON;
	}
	
}
