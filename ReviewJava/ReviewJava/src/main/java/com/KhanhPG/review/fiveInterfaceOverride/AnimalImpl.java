package com.KhanhPG.review.fiveInterfaceOverride;

/**
 * @author: KhanhPG
 * @since: 10/03/2022
 */
public final class AnimalImpl implements AnimalService {
	@Override
	public void meoSay() {
		System.out.println("meo meo");
	}

	@Override
	public void tigerSay() {
		System.out.println("Gào Gào");
	}

	public static void main(String[] args) {
		AnimalImpl animalImpl = new AnimalImpl();
		animalImpl.meoSay();
		animalImpl.dogSay();
		
	}
}
