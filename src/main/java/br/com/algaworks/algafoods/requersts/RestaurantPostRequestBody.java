package br.com.algaworks.algafoods.requersts;

import java.math.BigDecimal;

import br.com.algaworks.algafoods.domain.Kitchen;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantPostRequestBody {

    private String name;
    private BigDecimal freight;
	private Kitchen kitchen;

}
