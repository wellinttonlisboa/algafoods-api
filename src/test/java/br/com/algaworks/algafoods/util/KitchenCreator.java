package br.com.algaworks.algafoods.util;

import br.com.algaworks.algafoods.domain.Kitchen;

public class KitchenCreator {

	public static Kitchen createKitchenToBeSaved() {
		return Kitchen.builder().name("Brasileira").build();
	}

	public static Kitchen createValidKitchen() {
		return Kitchen.builder().name("Brasileira").id(3L).build();
	}

	public static Kitchen createValidUpdatedKitchen() {
		return Kitchen.builder().name("Brasil").id(3L).build();
	}
}
