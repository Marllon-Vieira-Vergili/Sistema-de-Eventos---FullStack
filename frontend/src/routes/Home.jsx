import React from 'react';

//Import dos componentes
import Navbar from '../components/Navbar';
import PartyCard from '../components/PartyCard';

//Import dos Hooks
import { useNavigate } from 'react-router-dom';
import useFetch from '../hooks/useFetch';

//Importando a API de conexão
import apiConnection from '../services/api';
import NewParty from './NewParty';

//Import dos CSS
import '../styles/Home.css';



const Home = () => {

  //Segundo - Criar os estates
  const navigate = useNavigate();

  //Usando o Hook customizado para buscar todas as festas
  //O hook gerencia automaticamente loading, erro e dados
  const {data: parties, loading, error, refetch} = useFetch('/parties');

  /**Função para deletar uma festa
   * Esta função é passada para o PartyCard, permitindo
   * que ele solicite a exclusão de uma festa
   */

  const handleDelete = async(partyId) => {
    try{

      //Faz a requisição DELETE para o backend
      await apiConnection.delete(`parties/${partyId}`);
      //Após deletar com sucesso, recarrega a lista
      refetch();

      alert("Evento excluído com sucesso!");
    }catch(err){
      console.error("Erro ao deletar festa:", err);
      alert('Erro ao excluir evento. Tente novamente');
    }
  };

  /**Função para navegar para a página de criar nova festa */
  const handleCreateParty = () => {
    navigate("/party/new");
  };

  //Enquanto está carregando, mostrar mensagem de carregamento
  if(loading){
    return(
      <div className='container-home'>
        <Navbar/>
        <div className='loading'>Carregando eventos....</div>
      </div>
    );
  };

  //Se houver erros
  if(error){
    return(
      <div className='container-home'>
        <Navbar/>
        <div className='error'>
          <p>Erro ao carregar eventos: {error}</p>
          <button onClick={refetch}>Tentar novamente</button>
        </div>
      </div>
    );
  };

  //Se não houver eventos cadastrados, incentivar a criar
  if(!parties || parties.length === 0){
    return(
      <div className='container-home'>
        <Navbar/>
        <div className='main-page-home'>
          <div className='empty-state'>
            <h2>Nenhum evento cadastrado</h2>
            <p>Você ainda não possui nenhum evento. Que tal criar um agora?</p>
            <button className='btn-create' onClick={handleCreateParty}>
              Criar primeiro evento
            </button>
          </div>
        </div>
      </div>
    );
  };

  //Se tudo tiver ok, exive a grid de festas

  //Primeiro - Fazer já o esqueleto
  return (
    <div className='container-home'>
      <Navbar/>
      {/**Página principal da Home */}
      <div className='main-page-home'>
        <div className='page-header'>
          <h1>Meus Eventos</h1>
          <button className='btn-create'
          onClick={handleCreateParty}
          >
            
            Criar novo evento
          </button>
        </div>

        {/**Grid dos cards */}
        <div className='parties-grid'>
          {parties.map((party) => (
            <PartyCard key={party.id}
            party={party}
            onDelete={handleDelete}/>
          ))}
        </div>
      </div>

      
    </div>
  )
}

export default Home;