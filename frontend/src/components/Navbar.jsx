import React from 'react'
import {Link} from 'react-router-dom';

/**Componente de navegação principal
 * Irá usar o link do react-router-dom para navegação sem recarregar a página
 */

import '../styles/Navbar.css';

const Navbar = () => {
  return (

    <div className='navbar-container'>
      <nav className='navbar'>
          <div className='nav-itens'>
              <p>Suas Festas</p>

              <div className='nav-buttons'>
                <ul>
                  <li>
                    {/**Link funciona como <a>, mas sem recarregar a página */}
                    <Link to="/">
                    <button>Minhas Festas</button>
                    </Link>
                    <Link to="/party/new">
                      <button className='btn-new-party'>Criar Nova Festa</button>
                    </Link>
                  </li>
                </ul>
              </div>
          </div>
      </nav>
    </div>
  )
}

export default Navbar;