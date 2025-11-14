package com.marllon.vieira.vergili.eventos.gerenciamento_eventos.controllers;

import com.marllon.vieira.vergili.eventos.gerenciamento_eventos.dto.request.PartyRequest;
import com.marllon.vieira.vergili.eventos.gerenciamento_eventos.dto.response.PartyResponse;
import com.marllon.vieira.vergili.eventos.gerenciamento_eventos.services.PartyServiceInterface;
import jakarta.servlet.http.Part;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/parties")
public class PartyController {

    @Autowired
    private PartyServiceInterface partyServiceInterface;


    /***ENDPOINT PARA ENVIAR AS INFORMAÇÕES PARA CRIAR O EVENTO.
     *
     * @Valid para ativar as validações
     * ***/
    @PostMapping
    public ResponseEntity<PartyResponse> createParty( @Valid @RequestBody PartyRequest partyRequest){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(partyServiceInterface.createParty(partyRequest));
    }

    @GetMapping(value = "/{title}")
    public ResponseEntity<PartyResponse> findByTitle(@PathVariable String title){

        return ResponseEntity.status(HttpStatus.FOUND).body(partyServiceInterface.findPartyByTitle(title));
    }

    @GetMapping
    public List<PartyResponse> findAll(){
        return partyServiceInterface.getAllParty();
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PartyResponse> deleteParty(@PathVariable Long id){

        
        return ResponseEntity.status(HttpStatus.OK).body(partyServiceInterface.deleteParty(id));

    }
}
