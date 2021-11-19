package br.com.algaworks.algafoods.requersts;

import java.time.LocalDateTime;
import java.util.Set;

import br.com.algaworks.algafoods.domain.Address;
import br.com.algaworks.algafoods.domain.Kitchen;
import br.com.algaworks.algafoods.domain.PaymentMethod;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantPutRequestBody {

	private Long id;
	private String name;
	private Long freight;
	private Kitchen kitchen;
	private Address address;
	private Set<PaymentMethod> payments;
	private LocalDateTime createdAt;

}
