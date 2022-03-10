package com.KhanhPG.review.main;

/**
 * @author: KhanhPG
 * @since: 10/03/2022
 */
public class CheckNonStaticInStatic {
	static {
		String a = "số 1";
		System.out.println(a);
	}
	
	public static void hello() {
		String b = "Hello";
		System.err.println(b);
	}
	public static void main(String[] args) {
		CheckNonStaticInStatic.hello();
		System.out.println("số 2");
	}

	static {
		String a = "sô 3";
		System.out.println(a);
	}
	// Bắt buộc phải có hàm main mới chạy chương trình, ko có > Error: Main method not found in class...
	// Không thể đổi tên main và String[]
	// Có thể đổi trên args
	// static chạy trướcs
}