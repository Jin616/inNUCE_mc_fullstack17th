package testonjava;

import java.util.HashMap;

public class HashMapTest {

	public static void main(String[] args) {

		HashMap<String, String> test = new HashMap<>();

		testPut(test);

		test.forEach((key, value) -> {
			System.out.print("key is : " + key);
			System.out.println(", value is : " + value);
		});
	}

	private static void testPut(HashMap<String, String> test) {
		test.put("key1", "val1");
		test.put("key2", "val2");
		test.put("key3", "val3");
		test.put("key4", "val4");
		test.put("key5", "val5");
		test.put("key6", "val6");
		test.put("key7", "val7");
		test.put("key8", "val8");
		test.put("key9", "val9");
	}
}
