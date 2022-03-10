package com.KhanhPG.review.fiveInterfaceOverride;

import org.springframework.stereotype.Service;

/**
 * @author: KhanhPG
 * @since: 10/03/2022
 */
@Service
public interface AnimalService {
	
	void meoSay();
	
	default void dogSay() {
		System.out.println("gau gau");
	}
	static void birdSay() {
		System.out.println("chip chip");
	};
	@SuppressWarnings("unused")
	private void lionSay() {
		System.out.println("lion");
	};
    void tigerSay();
    
}
