package testonjava;

import java.time.LocalDate;

public class LocalDateTest {

	public static void main(String[] args) {
		
		LocalDate ds = LocalDate.now();
		LocalDate de = LocalDate.now();
		
		System.out.printf("ds : %s\nde : %s\n동등연산자(==) 계산 결과는 ", ds, de);
		if(ds == de)
			System.out.println("같다.");
		else
			System.out.println("다르다.");
		
		System.out.print("equals 계산 결과는 ");
		if(ds.equals(de))
			System.out.println("같다.");
		else
			System.out.println("다르다.");
		
		de = de.minusDays(1);
		System.out.printf("de에서 -1 day를 해준 후\nds : %s\nde : %s\n동등연산자(==) 계산 결과는 ", ds, de);
		if(ds == de)
			System.out.println("같다.");
		else
			System.out.println("다르다.");
		
		System.out.print("equals 계산 결과는 ");
		if(ds.equals(de))
			System.out.println("같다.");
		else
			System.out.println("다르다.");
		
		ds = ds.minusDays(1);
		System.out.printf("ds에서도 -1 day를 해준 후\nds : %s\nde : %s\n동등연산자(==) 계산 결과는 ", ds, de);
		if(ds == de)
			System.out.println("같다.");
		else
			System.out.println("다르다.");
		
		System.out.print("equals 계산 결과는 ");
		if(ds.equals(de))
			System.out.println("같다.");
		else
			System.out.println("다르다.");
	}
}
