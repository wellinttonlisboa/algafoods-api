package br.com.algaworks.algafoods.requersts;

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
    private Long freight;
	private Kitchen kitchen;

}
