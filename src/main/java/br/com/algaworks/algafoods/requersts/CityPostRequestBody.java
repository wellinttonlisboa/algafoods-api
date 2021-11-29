package br.com.algaworks.algafoods.requersts;

import javax.validation.constraints.NotNull;

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

	@NotNull(message = "The city name cannot be null")
    private String name;
	@NotNull(message = "The state cannot be null")
	private State state;

}
