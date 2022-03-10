package com.KhanhPG.review.string;


/**
 * @author: KhanhPG
 * @since: 10/03/2022
 */
public class StringStringBuilderStringBuffer {
	
	public static void main(String[] args) {
		String a = "ab";
		String aa = new String("ab");
		String aaa = "a" + "b";
		String aaaa = "a".concat("b");
				
		
		System.out.println(System.identityHashCode(a));
		System.out.println(System.identityHashCode(aa));
		System.out.println(System.identityHashCode(aaa));
		System.out.println(System.identityHashCode(aaaa));
//		
//		System.out.println(a == af);
		System.out.println(a == aaa);
		
//		StringBuffer aBuffer = new StringBuffer("a");
//		StringBuilder aBuilder = new StringBuilder("a");z`
//
//		System.out.println(aBuffer.hashCode());
//		System.out.println(aBuilder.hashCode());
//		
//		// Chuyển về toString
//		System.out.println(aBuffer.toString().hashCode());
//		System.out.println(aBuilder.toString().hashCode());
		
		//  =  vùng nhớ string pool 
		// 	new String / new StringBuilder/ new StringBuffer trong vùng nhớ head ngoài string pool
		//	khi chuyển giá trị về toString() thì sẽ gán biến đó cho vùng nhớ string pool
	}
}
