package br.com.algaworks.algafoods.requersts;

import br.com.algaworks.algafoods.domain.Kitchen;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantPutRequestBody {

	private Long id;
	private String name;
	private Long freight;
	private Kitchen kitchen;

}
