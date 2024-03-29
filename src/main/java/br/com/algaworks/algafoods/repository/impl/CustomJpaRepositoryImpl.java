package br.com.algaworks.algafoods.repository.impl;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import br.com.algaworks.algafoods.repository.CustomJpaRepository;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID>
implements CustomJpaRepository<T, ID> {

private EntityManager manager;

public CustomJpaRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, 
		EntityManager entityManager) {
	super(entityInformation, entityManager);
	
	this.manager = entityManager;
}

@Override
public Optional<T> buscarPrimeiro() {
	var jpql = "from " + getDomainClass().getName();
	
	T entity = manager.createQuery(jpql, getDomainClass())
		.setMaxResults(1)
		.getSingleResult();
	
	return Optional.ofNullable(entity);
}

}