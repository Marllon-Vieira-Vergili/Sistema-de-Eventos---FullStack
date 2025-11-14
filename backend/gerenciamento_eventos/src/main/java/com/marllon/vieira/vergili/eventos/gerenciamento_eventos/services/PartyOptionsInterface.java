package com.marllon.vieira.vergili.eventos.gerenciamento_eventos.services;

import com.marllon.vieira.vergili.eventos.gerenciamento_eventos.dto.request.PartyOptionsRequest;
import com.marllon.vieira.vergili.eventos.gerenciamento_eventos.dto.response.PartyOptionsResponse;

import java.util.List;

/**INTERFACE CRUD PARA CRIAR OS MÉTODOS DA ENTIDADE PARTY*/
public interface PartyOptionsInterface {


    /*Criar um novo serviço de evento */
    public PartyOptionsResponse createPartyService(PartyOptionsRequest newParty);

    /*Localizar um serviço pelo seu título*/
    public PartyOptionsResponse findPartyServiceByName(String name);

    public List<PartyOptionsResponse> findAllParties();
    /*Alterar o nome de um serviço*/


    /*Deletar um serviço de festa*/
    public PartyOptionsResponse deletePartyService(Long id);
}
