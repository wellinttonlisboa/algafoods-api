package br.com.algaworks.algafoods.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.algaworks.algafoods.domain.Kitchen;
import br.com.algaworks.algafoods.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    List<State> findByName(String name);
    List<State> findByNameContaining(String name);
}
