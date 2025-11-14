import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'

import {createBrowserRouter, RouterProvider } from 'react-router-dom';
import Home from './routes/Home.jsx';
import NewParty from './routes/NewParty.jsx';

//Criando as rotas da aplicacao

const routes = createBrowserRouter([
  {
    path: "/",
    element: <Home/>
  },
  {
    path: "/party/new",
    element: <NewParty/>,
  },
]);


createRoot(document.getElementById('root')).render(
  <StrictMode>
    <RouterProvider router={routes}/>
  </StrictMode>,
)
