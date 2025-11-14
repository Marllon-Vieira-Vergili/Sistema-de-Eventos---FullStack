package com.marllon.vieira.vergili.eventos.gerenciamento_eventos.services;

import com.marllon.vieira.vergili.eventos.gerenciamento_eventos.dto.request.PartyRequest;
import com.marllon.vieira.vergili.eventos.gerenciamento_eventos.dto.response.PartyResponse;
import com.marllon.vieira.vergili.eventos.gerenciamento_eventos.entities.Party;
import com.marllon.vieira.vergili.eventos.gerenciamento_eventos.repository.PartyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class PartyService implements PartyServiceInterface {

    @Autowired
    private PartyRepository partyRepository;

    @Override
    public PartyResponse createParty(PartyRequest partyRequest) {


        //Criar o objeto
        Party party = new Party();

        //Criar os objetos, passando os valores pegos pela camada DTO
        party.setTitle(partyRequest.title());
        party.setAuthor(partyRequest.author());
        party.setDescription(partyRequest.description());
        party.setBudget(partyRequest.budget());
        party.setImage(partyRequest.image());

        //Salvar os objetos no banco de dados
        partyRepository.save(party);

        //Criar a resposta e retornar para o usuário que foi criado.

        //Retornar a resposta criada
        return new PartyResponse
                (party.getId(),
                partyRequest.title(),
                party.getAuthor(),
                party.getDescription(),
                party.getBudget(),
                party.getImage()
                );
    }

    @Override
    public PartyResponse findPartyByTitle(String title) {

        Party party = partyRepository.findByTitle(title);

        /*if (party.getTitle().equalsIgnoreCase(title) && !party.getTitle().isEmpty()){
            throw new NoSuchElementException("Não há nenhuma festa localizada com esse nome!");

        }
        */


        return new PartyResponse(party.getId(), party.getTitle(), party.getAuthor(), party.getDescription(),
                party.getBudget(), party.getImage());
    }

    @Override
    public List<PartyResponse> getAllParty() {

        List<Party> parties = partyRepository.findAll();

        if(parties.isEmpty()){
            throw new NoSuchElementException("Nenhuma festa localizada");
        }

        return (parties.stream().map(party ->
                new PartyResponse(party.getId(),
                        party.getTitle(),
                        party.getAuthor(),
                        party.getDescription(),
                        party.getBudget(),
                        party.getImage())).collect(Collectors.toList()));
    }

    @Override
    public PartyResponse updateParty(PartyRequest partyRequest) {

        List <Party> partyIdLocated = partyRepository.findAll();

        Party catchParty = partyIdLocated.getFirst();
        if (!partyIdLocated.isEmpty() && partyIdLocated.getFirst().getTitle().equalsIgnoreCase(partyRequest.title())){


            catchParty.setTitle(partyRequest.title());
            catchParty.setAuthor(partyRequest.author());
            catchParty.setDescription(partyRequest.description());
            catchParty.setImage(partyRequest.image());

            partyRepository.save(catchParty);
        }


        return new PartyResponse(catchParty.getId(),
                catchParty.getTitle(),
                catchParty.getAuthor(),
                catchParty.getDescription(),
                catchParty.getBudget(),
                catchParty.getImage());
    }

    @Override
    public PartyResponse deleteParty(Long id) {

        Party partyFounded = partyRepository.findById(id).orElseThrow();



       PartyResponse response = new PartyResponse(
               partyFounded.getId(),
                partyFounded.getTitle(),
                partyFounded.getAuthor(),
                partyFounded.getDescription(),
                partyFounded.getBudget(),
                partyFounded.getImage());

        partyRepository.delete(partyFounded);

        return response;
    }
}
