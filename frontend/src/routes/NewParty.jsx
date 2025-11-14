import React from 'react'
import { useNavigate } from 'react-router-dom'
import { useState } from 'react';
import Navbar from '../components/Navbar'
import apiConnection from '../services/api';
import '../styles/NewParty.css';

const NewParty = () => {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    title: '',
    author: '',
    description: '',
    budget: '',
    image: '',
  });

  const [errors, setErrors] = useState({});
  const [isSubmitting, setIsSubmitting] = useState(false);

  const handleChange = (e) => {
    const {name, value} = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));

    if(errors[name]){
      setErrors(prev => ({
        ...prev,
        [name]: ''
      }));
    }
  }

  const validateForm = () => {
    const newErrors = {};

    if(!formData.title.trim()){
      newErrors.title = 'O título é obrigatório!';
    }else if(formData.title.length < 3){
      newErrors.title = 'O título deve ter pelo menos 3 caracteres';
    }

    if(!formData.author.trim()){
      newErrors.author = "O autor é obrigatório";
    }

    if(!formData.description.trim()){
      newErrors.description = "A descrição é obrigatória!";
    }else if(formData.description.length < 10){
      newErrors.description = "A descrição deve ter pelo menos 10 caracteres";
    }

    if(!formData.budget){
      newErrors.budget = "O orçamento é obrigatório!";
    } else {
      const budgetNum = parseFloat(formData.budget);
      if (isNaN(budgetNum) || budgetNum <= 0) {
        newErrors.budget = 'O orçamento deve ser um valor positivo';
      } else if (budgetNum > 100000) {
        newErrors.budget = 'O orçamento não pode exceder R$ 100.000';
      }
    }

    if(!formData.image.trim()){
      newErrors.image = "A URL da imagem é obrigatória!";
    } else {
      try{
        new URL(formData.image);
      }catch{
        newErrors.image = "Por favor, insira uma URL válida";
      }
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  }

  const handleSubmit = async (e) => {
    e.preventDefault();

    if(!validateForm()){
      return;
    }

    setIsSubmitting(true);

    try{
      const dataToSend = {
        ...formData,
        budget: parseFloat(formData.budget)
      };

      // Adicionando log para debug
      console.log('Enviando dados:', dataToSend);

      const response = await apiConnection.post('/parties', dataToSend);
      
      console.log('Resposta do servidor:', response.data);
      
      alert('Evento criado com sucesso!');
      navigate('/');
      
    } catch(err) {
      console.error('Erro completo:', err);
      console.error('Resposta do erro:', err.response?.data);
      
      if (err.response?.data?.errors) {
        const backendErrors = {};
        err.response.data.errors.forEach(error => {
          backendErrors[error.field] = error.message;
        });
        setErrors(backendErrors);
      } else {
        alert(`Erro ao criar evento: ${err.response?.data?.message || err.message}`);
      }
    } finally {
      // IMPORTANTE: sempre resetar o isSubmitting no finally
      setIsSubmitting(false);
    }
  };

  const handleCancel = () => {
    if(window.confirm('Deseja realmente cancelar? Os dados não serão salvos.')){
      navigate('/');
    }
  };

  return (
    <div className='container-new-party'>
      <Navbar/>

      <div className='main-content'>
        <div className='form-container'>
            <h1>Criar Novo Evento</h1>

            <form className='party-form' onSubmit={handleSubmit}>

              <div className='form-group'>
                <label htmlFor='title'>
                  Título do Evento<span className='required'>*</span>
                </label>
                <input 
                  type='text'
                  id='title'
                  name='title'
                  value={formData.title}
                  onChange={handleChange}
                  className={errors.title ? 'input-error' : ''}
                  placeholder='Ex: Festa de Aniversário'
                />
                {errors.title && <span className='error-message'>{errors.title}</span>}
              </div>

              <div className='form-group'>
                <label htmlFor='author'>
                  Organizador<span className='required'>*</span>
                </label>
                <input 
                  type='text'
                  id='author'
                  name='author'
                  value={formData.author}
                  onChange={handleChange}
                  className={errors.author ? 'input-error' : ''}
                  placeholder='Digite seu nome'
                />
                {errors.author && <span className='error-message'>{errors.author}</span>}
              </div>

              <div className='form-group'>
                <label htmlFor='description'>
                  Descrição <span className='required'>*</span>
                </label>
                <textarea
                  id='description'
                  name='description'
                  value={formData.description}
                  onChange={handleChange}
                  className={errors.description ? 'input-error' : ''}
                  placeholder='Descreva o evento...'
                  rows='4'
                />
                {errors.description && <span className='error-message'>{errors.description}</span>}
              </div>

              <div className='form-group'>
                <label htmlFor='budget'>
                  Orçamento (R$) <span className='required'>*</span>
                </label>
                <input 
                  type='number'
                  id='budget'
                  name='budget'
                  value={formData.budget}
                  onChange={handleChange}
                  className={errors.budget ? 'input-error' : ''}
                  placeholder='0.00'
                  step='0.01'
                  min='0'
                  max='100000'
                />
                {errors.budget && <span className='error-message'>{errors.budget}</span>}
              </div>

              <div className='form-group'>
                <label htmlFor='image'>
                  URL da imagem <span className='required'>*</span>
                </label>
                <input 
                  type='text'
                  id='image'
                  name='image'
                  value={formData.image}
                  onChange={handleChange}
                  className={errors.image ? 'input-error' : ''}
                  placeholder='https://exemplo.com/imagem.jpg'
                />
                {errors.image && <span className='error-message'>{errors.image}</span>}
                
                {formData.image && !errors.image && (
                  <div className='image-preview'>
                    <img 
                      src={formData.image} 
                      alt='Preview' 
                      onError={(e) => {
                        e.target.style.display = 'none';
                      }}
                    />
                  </div>
                )}
              </div>

              <div className='form-actions'>
                <button 
                  type='button'
                  className='btn-cancel'
                  onClick={handleCancel}
                  disabled={isSubmitting}
                >
                  Cancelar
                </button>

                <button 
                  type='submit'
                  className='btn-submit'
                  disabled={isSubmitting}
                >
                  {isSubmitting ? 'Criando....' : 'Criar Evento'}
                </button>
              </div>
            </form>
        </div>
      </div>
    </div>
  );
};

export default NewParty;