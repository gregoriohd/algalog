package br.com.gregorio.algalog.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gregorio.algalog.domain.model.Entrega;

@Repository
public interface EntregaRespository extends JpaRepository<Entrega, Long> {

}
