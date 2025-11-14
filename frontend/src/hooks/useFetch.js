/**
 * Hook Customizado para buscar dados da API
 * Este Hook encapsula toda a lógica de requisição GET,
 * incluindo estados de loading, erro e os dados em si
 */
import { useState, useEffect } from "react";
import apiConnection from "../services/api";


const useFetch = (url, shouldFetch = true) => {
    //Estado para armazenar os dados recebidos da API
    const [data, setData] = useState(null);

    //Estado para controlar se está carregando
    const [loading, setLoading] = useState(true);

    //Estado para armazenar possíveis erros
    const [error, setError] = useState(null);


    //useEffect executa quando o componente monta ou quando url/shouldEffect mudam
    useEffect(() => {
        //Se não deve buscar, não faz nada
        if(!shouldFetch){
            setLoading(false);
            return;
        }

        //Função assíncrona para buscar os dados
        const fetchData = async () => {

            try{
                setLoading(true); //Inicia o loading
                setError(null); //Limpa os erros anteriores
                
                //Faz a requisição GET
                const response = await apiConnection.get(url);
                //Armazena os dados recebidos
                setData(response.data);
            }catch(err){
                //Se houver erro, armazena a mensagem
                setError(err.response?.data.message || 'Erro ao carregar dados');
                console.error('Erro na requisição: ', err);
            }finally{
                //Sempre para o loading, com sucesso ou erro
                setLoading(false);
            }
        };

        //Chamando o método
        fetchData();
    },[url,shouldFetch]); //Reexecuta se a url ou shouldFetch mudarem

    //Função para recarregar os dados manualmente
    const refetch = async () => {
        try{
            setLoading(true);
            setError(null);
            const response = await apiConnection.get(url);
            setData(response.data);
        }catch(err){
            setError(err.response?.data?.message || 'Erro ao carregar dados');
        }finally{
            setLoading(false);
        }
    };

    
//Retorna os estados e a função de refetch
return {data, loading, error, refetch};
};

export default useFetch;

