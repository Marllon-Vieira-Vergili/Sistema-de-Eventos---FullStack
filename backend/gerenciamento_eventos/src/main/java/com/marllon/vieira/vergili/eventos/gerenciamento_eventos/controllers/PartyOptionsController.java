package com.marllon.vieira.vergili.eventos.gerenciamento_eventos.controllers;

import com.marllon.vieira.vergili.eventos.gerenciamento_eventos.dto.request.PartyOptionsRequest;
import com.marllon.vieira.vergili.eventos.gerenciamento_eventos.dto.response.PartyOptionsResponse;
import com.marllon.vieira.vergili.eventos.gerenciamento_eventos.services.PartyOptionsInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/options")
public class PartyOptionsController {


    @Autowired
    private PartyOptionsInterface partyOptionsInterface;

    @PostMapping
    public ResponseEntity<PartyOptionsResponse> createPartyOption(@Valid  @RequestBody PartyOptionsRequest request){

        return ResponseEntity.status(HttpStatus.CREATED).body(partyOptionsInterface.createPartyService(request));
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<PartyOptionsResponse> getPartyOptionByName(@PathVariable String name){

        return ResponseEntity.status(HttpStatus.FOUND).body(partyOptionsInterface.findPartyServiceByName(name));
    }

    @GetMapping
    public List<PartyOptionsResponse> getAllPartiesServices(){
        return partyOptionsInterface.findAllParties();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PartyOptionsResponse> deletePartyOption(@PathVariable Long id){

        return ResponseEntity.status(HttpStatus.OK).body(partyOptionsInterface.deletePartyService(id));
    }

}
