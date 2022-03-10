package com.KhanhPG.review.main;

/**
 * @author: KhanhPG
 * @since: 10/03/2022
 */
public class CheckNonStaticInStatic {
	static {
		String a = "a";
		System.out.println(a);
	}
	
	public static void hello() {
		String b = "Hello";
		System.err.println(b);
	}
	public static void main(String[] args) {
		CheckNonStaticInStatic.hello();
	}

	static {
		String a = "a none static";
		System.out.println(a);
	}
	// Bắt buộc phải có hàm main mới chạy chương trình, ko có > Error: Main method not found in class...
	// Không thể đổi tên main và String[]
	// Có thể đổi trên args
	// static xếp theo thứ tự chạy	
}
