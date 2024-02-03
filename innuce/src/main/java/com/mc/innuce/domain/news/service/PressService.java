package com.mc.innuce.domain.news.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mc.innuce.domain.news.dao.PressDAO;
import com.mc.innuce.domain.news.dto.PressDTO;

/**
 * @author JIN 24/01/12
 */
@Service
public class PressService {

	@Autowired
	PressDAO pressDAO;
	/**
	 * 서버 실행 시 혹은 필요할 때마다 언론사 정보를 새로 받아와서 이 메서드를 실행. DB에 이미 언론사 키가 존재하고, overriding한
	 * equals 메서드를 통해 다른 객체임이 확인 되면 updateList에, DB에 언론사 키가 없다면 insertList에
	 * pressList의 dto 값들을 넣어준다. DB에 각각의 기능을 실행 후 결과값 출력 후 종료
	 */

	public void updatePressTable(List<PressDTO> pressList) {
		HashMap<Integer, PressDTO> originMap = new HashMap<Integer, PressDTO>();
		List<PressDTO> originList = getAllPressList();
		List<PressDTO> insertList = new ArrayList<PressDTO>();
		List<PressDTO> updateList = new ArrayList<PressDTO>();

		for (PressDTO dto : originList)
			originMap.put(dto.getPress_key(), dto);

		for (PressDTO dto : pressList) {
			if (originMap.containsKey(dto.getPress_key())) {
				if (!originMap.get(dto.getPress_key()).equals(dto))
					updateList.add(dto);
			} else {
				insertList.add(dto);
			}
		}

		int updateRow = 0, insertRow = 0;
		if (updateList.size() != 0)
			for (PressDTO dto : updateList)
				updateRow += pressDAO.updatePress(dto);
		if (insertList.size() != 0)
			insertRow += pressDAO.insertListPress(insertList);

		System.out.printf("update %d 건, insert %d 건 완료했습니다.", updateRow, insertRow);
	}

	public List<PressDTO> getAllPressList() {
		return pressDAO.selectAllPress();
	}

}
