/**
 * CRUDS DO ENDPOINT SPRINGBOOT DA PARTE DO PARTY
 */

import apiConnection from "./api";


export const createParty = async(partyData) => {

    try{
        const response = await apiConnection.post('/parties', partyData);
        return response.data;

    }catch(error){
        throw new error("Não foi possível criar o evento");
    }
}

export const getAllParties = async()=> {
    try{
        const response = await apiConnection.get('/parties');
        return response.data;

    }catch(error){
        throw new error("Não foi possível localizar nenhum evento");
    }
}

export const findPartyByTitle = async(partyTitle) => {

    try{
        const response = await apiConnection.get(`/parties/${partyTitle}`);
        return response.data;

    }catch(error){
        throw new error("Não foi possível localizar esse evento por este título");
    }
}

export const deleteParty = async (id) => {

       try{
        const response = await apiConnection.delete(`/parties/${id}`);
        return response.data;

    }catch(error){
        throw new error("Não foi possível remover este evento informado");
    }
}