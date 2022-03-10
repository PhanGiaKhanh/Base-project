package com.KhanhPG.review.string;


/**
 * @author: KhanhPG
 * @since: 10/03/2022
 */
public class StringStringBuilderStringBuffer {
	
	public static void main(String[] args) {
		String a = "a";
		String af = new String("a");
		String aa = "a" + "";
		
		StringBuffer aBuffer = new StringBuffer("a");
		StringBuilder aBuilder = new StringBuilder("a");
		
		System.out.println(a.hashCode());
		System.out.println(aa.hashCode());
		System.out.println(af.hashCode());
		
		System.out.println(a == af);
		System.out.println(a == aa);
		

		System.out.println(aBuffer.hashCode());
		System.out.println(aBuilder.hashCode());
		
		// Chuyển về toString
		System.out.println(aBuffer.toString().hashCode());
		System.out.println(aBuilder.toString().hashCode());
		
		//  =/new String() là trong vùng nhớ string pool
		// 	new StringBuilder/ new StringBuffer trong vùng nhớ head ngoài string pool
		//	khi chuyển giá trị về toString() thì sẽ gán biến đó cho vùng nhớ string pool
	}
}
