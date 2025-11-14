package com.marllon.vieira.vergili.eventos.gerenciamento_eventos.services;

import com.marllon.vieira.vergili.eventos.gerenciamento_eventos.dto.request.PartyOptionsRequest;
import com.marllon.vieira.vergili.eventos.gerenciamento_eventos.dto.response.PartyOptionsResponse;
import com.marllon.vieira.vergili.eventos.gerenciamento_eventos.entities.PartyOptions;
import com.marllon.vieira.vergili.eventos.gerenciamento_eventos.repository.PartyOptionsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class PartyOptionsService implements PartyOptionsInterface{

    @Autowired
    private PartyOptionsRepository partyOptionsRepository;


    @Override
    public PartyOptionsResponse createPartyService(PartyOptionsRequest newParty) {

        PartyOptions options = new PartyOptions();
        options.setName(newParty.name());
        options.setDescription(newParty.description());
        options.setPrice(newParty.price());
        options.setImage(newParty.image());

        partyOptionsRepository.save(options);


        return new PartyOptionsResponse(
                options.getId(),
                options.getName(),
                options.getDescription(),
                options.getPrice(),
                options.getImage());
    }

    @Override
    public PartyOptionsResponse findPartyServiceByName(String name) {

        PartyOptions options = partyOptionsRepository.findByName(name);

        /*
        if(name.isEmpty() || !options.getName().equalsIgnoreCase(name)){
            throw new NoSuchElementException("Não foi encontrado nenhum serviço de evento com este nome");

        }
         */

        return new PartyOptionsResponse(options.getId(),
                options.getName(),
                options.getDescription(),
                options.getPrice(), options.getImage());
    }

    @Override
    public List<PartyOptionsResponse> findAllParties() {

        List<PartyOptions> partiesOptions = partyOptionsRepository.findAll();

        if (partiesOptions.isEmpty()){
            throw new NoSuchElementException("Nenhum evento cadastrado");
        }

        return partiesOptions.stream().map(partyOptions -> new PartyOptionsResponse(partyOptions.getId(),
                partyOptions.getName(), partyOptions.getDescription(), partyOptions.getPrice(), partyOptions.getImage())).toList();
    }

    @Override
    public PartyOptionsResponse deletePartyService(Long id) {

        PartyOptions options = partyOptionsRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Nenhum elemento encontrado no banco de dados"));

        partyOptionsRepository.delete(options);

        return new PartyOptionsResponse(options.getId(),
                options.getName(),options.getDescription(),
                options.getPrice(), options.getImage());
    }
}
