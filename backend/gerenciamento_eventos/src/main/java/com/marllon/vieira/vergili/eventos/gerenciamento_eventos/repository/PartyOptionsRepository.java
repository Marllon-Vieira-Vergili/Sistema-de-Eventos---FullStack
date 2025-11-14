package com.marllon.vieira.vergili.eventos.gerenciamento_eventos.repository;

import com.marllon.vieira.vergili.eventos.gerenciamento_eventos.entities.PartyOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Interface para extender os repositórios do JPA
@Repository
public interface PartyOptionsRepository extends JpaRepository<PartyOptions, Long> {

    PartyOptions findByName(String name);
}
