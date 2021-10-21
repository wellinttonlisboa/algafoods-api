package br.com.algaworks.algafoods.util;

import br.com.algaworks.algafoods.requersts.KitchenPostRequestBody;

public class KitchenPostRequestBodyCreator {
	public static KitchenPostRequestBody createKitchenPostRequestBody() {
		return KitchenPostRequestBody.builder().name(KitchenCreator.createKitchenToBeSaved().getName()).build();
	}
}
