package com.algaworks.algalog.domain.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.algalog.domain.model.Cliente;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	List<Cliente> findByNome(String nome);
	List<Cliente> findByNomeContaining(String nome);

	Optional<Cliente> findByEmail(String email);
}
