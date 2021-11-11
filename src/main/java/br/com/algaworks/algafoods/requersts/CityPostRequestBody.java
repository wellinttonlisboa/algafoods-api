package br.com.algaworks.algafoods.requersts;

import br.com.algaworks.algafoods.domain.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityPostRequestBody {

    private String name;
	private State state;

}
