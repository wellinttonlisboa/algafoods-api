package br.com.algaworks.algafoods.requersts;

import java.math.BigDecimal;

import br.com.algaworks.algafoods.domain.Kitchen;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantPutRequestBody {

	private Long id;
	private String name;
	private BigDecimal freight;
	private Kitchen kitchen;

}
