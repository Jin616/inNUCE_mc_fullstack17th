package testonjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(5, 4, 3, 2, 1));
		// value가 1인 element 삭제        
		list.remove(Integer.valueOf(1));         
		System.out.println(list); 
	// [5, 4, 3, 2, 1]    
	}
}
