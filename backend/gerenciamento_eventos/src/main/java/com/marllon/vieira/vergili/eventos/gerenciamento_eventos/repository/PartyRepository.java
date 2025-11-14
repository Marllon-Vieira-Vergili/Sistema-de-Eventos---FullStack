package com.marllon.vieira.vergili.eventos.gerenciamento_eventos.repository;

import com.marllon.vieira.vergili.eventos.gerenciamento_eventos.entities.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
//Interface para extender os repositórios do JPA
public interface PartyRepository extends JpaRepository<Party, Long> {


    Party findByTitle(String title);
}
