package br.com.algaworks.algafoods.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class Address {
	
	@Column(name = "address_zipCode")
	private String zipCode;
	
	@Column(name = "address_publicPlace")
	private String publicPlace;
	
	@Column(name = "address_number")
	private String number;
	
	@Column(name = "address_complement")
	private String complement;
	
	@ManyToOne
	@JoinColumn(name = "address_city_id")
	private City city;

}
