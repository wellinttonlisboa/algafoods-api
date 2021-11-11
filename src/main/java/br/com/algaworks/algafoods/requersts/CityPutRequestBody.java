package br.com.algaworks.algafoods.requersts;

import br.com.algaworks.algafoods.domain.State;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityPutRequestBody {

	private Long id;
	private String name;
	private State state;

}
