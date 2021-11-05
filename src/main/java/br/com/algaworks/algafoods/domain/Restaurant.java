package br.com.algaworks.algafoods.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

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
public class Restaurant {

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "The restaurant name cannot be empty")
	@Column(nullable = false, unique = true)
	private String name;

	@NotEmpty(message = "The restaurant freight cannot be empty")
	@Column(nullable = false)
	private BigDecimal freight;

	@ManyToOne(optional = false)
	private Kitchen kitchen;

}
