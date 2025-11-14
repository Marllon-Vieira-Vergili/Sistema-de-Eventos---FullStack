/**
 * CRUDS DO ENDPOINT SPRINGBOOT DA PARTE DO PARTY Service
 */

import apiConnection from "./api";


export const createPartyOption = async(optionData) => {

    try{
        const response = await apiConnection.post("/options", optionData);
        return response.data;

    }catch(error){
        throw new error("Não foi possível criar a opção deste evento");
    }
}

export const getPartyOptionByName = async(partyName) => {

     try{
        const response = await apiConnection.post(`/options/${partyName}`);
        return response.data;

    }catch(error){
        throw new error("Não foi possível localizar esse serviço de evento");
    }
}

export const getAllPartiesService = async() => {

        try{
        const response = await apiConnection.post('/options');
        return response.data;

    }catch(error){
        throw new error("Não foi possível localizar nenhum serviço de evento");
    }
}

export const deletePartyService = async(id) => {

        try{
        const response = await apiConnection.delete(`/options/${id}`);
        return response.data;

    }catch(error){
        throw new error("Não foi possível deletar esse serviço de evento");
    }
}