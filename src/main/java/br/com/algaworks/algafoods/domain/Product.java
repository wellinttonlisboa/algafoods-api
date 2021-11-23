package br.com.algaworks.algafoods.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import br.com.algaworks.algafoods.util.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "The city name cannot be empty")
	@Column(nullable = false)
	private String name;
	
	@NotEmpty(message = "The desscription cannot be empty")
	@Column(nullable = false)
	private String description;
	
	@NotEmpty(message = "The price cannot be empty")
	@Column(nullable = false)
	private BigDecimal price;
	
	@ManyToOne(optional = false)
	private Status status;
	
	@ManyToOne(optional = false)
	private Restaurant restaurant;
}
