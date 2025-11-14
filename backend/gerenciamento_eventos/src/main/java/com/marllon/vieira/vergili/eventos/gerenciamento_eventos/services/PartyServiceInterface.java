package com.marllon.vieira.vergili.eventos.gerenciamento_eventos.services;


import com.marllon.vieira.vergili.eventos.gerenciamento_eventos.dto.request.PartyRequest;
import com.marllon.vieira.vergili.eventos.gerenciamento_eventos.dto.response.PartyResponse;

import java.util.List;

/**INTERFACE CRUD PARA CRIAR OS MÉTODOS DA ENTIDADE PARTY*/
public interface PartyServiceInterface {

    /*Criar um evento*/
    public PartyResponse createParty(PartyRequest partyRequest);

    /*Localizar um evento pelo seu título*/
    public PartyResponse findPartyByTitle(String title);

    /*Encontrar todos os eventos criados do usuário*/
    public List<PartyResponse> getAllParty();

    /*Editar um evento*/
    public PartyResponse updateParty(PartyRequest partyRequest);

    /*Remover um evento*/
    public PartyResponse deleteParty(Long id);

}
