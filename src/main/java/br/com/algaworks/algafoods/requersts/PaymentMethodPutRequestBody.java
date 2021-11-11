package br.com.algaworks.algafoods.requersts;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentMethodPutRequestBody {
	
    private Long id;
    private String name;
    
}
