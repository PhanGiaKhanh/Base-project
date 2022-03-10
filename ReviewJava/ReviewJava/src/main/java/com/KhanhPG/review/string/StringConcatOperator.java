package com.KhanhPG.review.string;

import java.util.Objects;


/**
	* @author: KhanhPG
	* @since: 10/03/2022	
	*/
public class StringConcatOperator {
	public static void main(String[] args) {
		String a = "a";
		String b = "b";
		
		String string3 = "ab";
		String string1 = a + b;
		String string2 = a.concat(b);
		System.out.println(string1.hashCode());
		System.out.println(string2.hashCode());
		System.out.println(string3.hashCode());
		System.out.println("Bien a = " + a);
		System.out.println("Bien b = " + b);
		System.out.println("Bien string1 = a + b = " + string1);
		System.out.println("Bien string2 = a.concat(b) = " + string2);
		System.out.println("Bien string3 = " + string3);
		System.out.println("========================");
		System.out.println("So sanh equals string1.equals(string2) = " + string1.equals(string2));
		System.out.println("So sanh equals string3.equals(string1) = " + string3.equals(string1));
		System.out.println("So sanh equals string3.equals(string2) = " + string3.equals(string2));
		
		System.out.println("So sanh (==) string1 == string2 = " + (Objects.equals(string1, string2)) );
		System.out.println("So sanh (==) string3 == string1 = " + (string3 == string1));
		System.out.println("So sanh (==) string3 == string2 = " + (string3 == string2) );
		

		String string4 = a + "";
		String string5 = a.concat("");
		System.out.println("So sanh equals (a+ \"\").equals(a) = " + string4.equals(a));
		System.out.println("So sanh (==) a + \"\" == a = " + (string4 == a) );
		
		
		System.out.println("So sanh equals (a.concat(\"\")).equals(a) = " + string5.equals(a));
		System.out.println("So sanh (==) a.concat(\"\") == a = " + (string5 == a) );
		
		
		// Concat() và (+) Không tạo ra vùng nhớ mới
		// Không nên sử dụng toán tử == với hai String
	}
}
