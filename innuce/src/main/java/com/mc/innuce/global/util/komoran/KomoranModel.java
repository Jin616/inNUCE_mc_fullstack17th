package com.mc.innuce.global.util.komoran;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;

public class KomoranModel {
	private static KomoranModel instance;
	private Komoran komoran;
	
	public static void main(String[] args) {
		KomoranModel km = KomoranModel.getInstance();
	
	}
	
	private KomoranModel() {
		komoran = new Komoran(DEFAULT_MODEL.FULL);
		String path = System.getProperty("user.dir");
		System.out.println(path);
		komoran.setFWDic(path + "/src/main/resources/static/dictionary/fwd.user");
	}
	
	public static KomoranModel getInstance() {
		if(instance == null) {
			synchronized (KomoranModel.class) {
				instance = new KomoranModel();
			}
		}
		
		return instance;
	}

	public Komoran getKomoran() {
		return komoran;
	}
}
