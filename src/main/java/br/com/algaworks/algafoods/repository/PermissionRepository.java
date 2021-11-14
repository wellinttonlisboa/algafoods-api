package br.com.algaworks.algafoods.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.algaworks.algafoods.domain.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    List<Permission> findByName(String name);
    List<Permission> findByNameContaining(String name);
    
}
