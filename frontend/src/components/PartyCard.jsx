/**Componente PartyCard Reponsável por renderizar os tipos de eventos 
 

1 - Crie o jsx, faça ele todo primeiro, imaginando-o
2 - depois, crie as functions, states, e dê o nome que achar melhor.
3 - Verifique o que será passado via props para os outros componentes
 */

import React from 'react';

import '../styles/PartyCard.css';

const PartyCard = ({party, onDelete}) => {

//Função para formatar o valor monetário em Real Brasileiro
const formatCurrency = (value) => {

    return new Intl.NumberFormat('pt-BR', {
       style: 'currency',
       currency: 'BRL', 
    }).format(value);
};

//Função para confirmar antes de deletar
const handleDelete = () => {
    //Pede confirmação ao usuário
    const confirmed = window.confirm(`Tem certeza que deseja
        excluir o evento? "${party.title}"?`);

        if(confirmed){
            onDelete(party.id); //Chama a função passada pelo componente pai
        }
};


  return (

    <div className='party-card'>
        {/**Imagem do evento */}
        <div className='party-img'>
            <img src={party.image || 'https://via.placeholder.com/300x200'}
            alt={party.title}
            onError={(e) => {
                //Se a imagem falhar ao carregar, usar placeholder
                e.target.src = 'https://via.placeholder.com/300x200?text=Sem+mImagem';
            }}
            />
        </div>

        {/**Conteúdo abaixo da imagem */}
        <div className='party-content'>
            <h3>{party.title}</h3>
            <p className='party-author'>{party.author}</p>
            <p className='party-description'>{party.description}</p>
        </div>
        {/*Rodapé */}
        <div className='party-footer'>
            <span className='party-budget'>
                Orçamento: R$ {formatCurrency(party.budget)}
            </span>

            <button className='btn-delete'
            onClick={handleDelete}
            title='Excluir evento'
            >   
            Excluir
            </button>
        </div>
    </div>
  );
};

export default PartyCard