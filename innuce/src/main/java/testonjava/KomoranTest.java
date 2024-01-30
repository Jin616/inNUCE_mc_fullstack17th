package testonjava;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.shineware.nlp.komoran.model.Token;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;

public class KomoranTest {
	
	static String[] type = {"NNG","NNP","SL"};
	
	public static void main(String[] args) {
		Komoran komoran = new Komoran(DEFAULT_MODEL.LIGHT);
        String strToAnalyze = "[이데일리 권혜미 기자] 김정은 북한 국무위원장이 2억6000만원 대의 메르세데스-벤츠 차량을 타고 등장했다.\r\n"
        		+ "\r\n"
        		+ "지난 15일 북한 조선중앙TV가 방영한 기록영화 ‘위대한 전환, 승리와 변혁의 2023년’에서 김 위원장이 검은색 스포츠유틸리티(SUV) 차에서 내리는 장면이 포착됐다. 차량 우측 뒷좌석 문에는 ‘국무위원장’ 마크가 새겨져있다.\r\n"
        		+ "\r\n"
        		+ "해당 차량은 벤츠가 생산하는 SUV 가운데 최고급인 GLS에 벤츠의 상위급 브랜드인 마이바흐 라벨을 달고 출시한 ‘메르세데스-마이바흐 GLS 600’으로 추정된다. 국내에는 2021년 3월 2일에 공식 출시됐고, 가격은 2억 5660만원부터 시작한다.국내 가격은 2억6000만원대에서 시작한다.\r\n"
        		+ "\r\n"
        		+ "이같이 사치품에 해당하는 고가 차량은 물론, 운송 수단의 북한 반입 자체가 유엔 안전보장이사회 대북 제재 결의 위반이다. 하지만 김 위원장은 여러 차종의 벤츠 차량을 공개하며 논란이 됐다.";

        KomoranResult analyzeResultList = komoran.analyze(strToAnalyze);

        List<Token> tokenList = analyzeResultList.getTokenList();
        // original code
        for (Token token : tokenList) {
            System.out.format("(%2d, %2d) %s/%s\n", token.getBeginIndex(), token.getEndIndex(), token.getMorph(), token.getPos());
        }
        System.out.println(analyzeResultList.toString());
        
        // test code
        Map<String, Integer> map = new HashMap<String, Integer>();
        
        String str = "";
        for(Token t : tokenList) {
        	if(checkType(t.getPos())) {
        		if((str = t.getMorph()).length() == 1)
        			continue;
        		if(map.containsKey(str))
        			map.put(str, map.get(str)+1);
        		else
        			map.put(str, 1);
        	}
        }
        
        str = "";
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
        	if(entry.getKey().length() == 1)
        		continue;
        	str += ",\"x\":\"" + entry.getKey() + "\", " + "\"" + entry.getValue() + "\""; 
        }
        
        System.out.println("{" + str.replaceFirst(",", "") + "}");
        
	}

	private static boolean checkType(String pos) {
		for(String s : type)
			if(s.equals(pos))
				return true;
		return false;
	}
	
}

//대분류	소분류	세분류
//체언	명사NN	일반명사NNG
//고유명사NNP
//의존명사NNB
//대명사NP	대명사NP
//수사NR
//용언	동사VV	동사VV
//형용사VA	형용사VA
//보조용언VX	보조용언VX
//지정사VC	긍정지정사VCP
//부정지정사VCN
//수식언	관형사MM	관형사MM
//부사MA	일반부사MAG
//접속부사MAJ
//독립언	감탄사IC	감탄사IC
//관계언	격조사JK	주격조사JKS
//보격조사JKC
//관형격조사JKG
//목적격조사JKO
//부사격조사JKB
//호격조사JKV
//인용격조사JKQ
//보조사JX	보조사JX
//접속조사JC	접속조사JC
//의존형태	어미E	선어말어미EP
//종결어미EF
//연결어미EC
//명사형전성어미ETN
//관형형전성어미ETM
//접두사XP	체언접두사XPN
//접미사XS	명사파생접미사XSN
//동사파생접미사XSV
//형용사파생접미사XSA
//어근XR	어근XR
//기호	마침표,물음표,느낌표SF	마침표,물음표,느낌표SF
//쉼표,가운뎃점,콜론,빗금SP	쉼표,가운뎃점,콜론,빗금SP
//따옴표,괄호표,줄표SS	따옴표,괄호표,줄표SS
//줄임표SE	줄임표SE
//붙임표(물결,숨김,빠짐)SO	붙임표(물결,숨김,빠짐)SO
//외국어SL	외국어SL
//한자SH	한자SH
//기타기호(논리수학기호,화폐기호)SW	기타기호(논리수학기호,화폐기호)SW
//명사추정범주NF	명사추정범주NF
//용언추정범주NV	용언추정범주NV
//숫자SN	숫자SN
//분석불능범주NA	분석불능범주NA