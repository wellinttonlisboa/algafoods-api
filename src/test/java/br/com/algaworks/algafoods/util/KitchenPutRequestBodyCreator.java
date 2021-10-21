package br.com.algaworks.algafoods.util;

import br.com.algaworks.algafoods.requersts.KitchenPutRequestBody;

public class KitchenPutRequestBodyCreator {
	public static KitchenPutRequestBody createKitchenPutRequestBody() {
		return KitchenPutRequestBody.builder().id(KitchenCreator.createValidUpdatedKitchen().getId())
				.name(KitchenCreator.createValidUpdatedKitchen().getName()).build();
	}
}
