
import { Outlet } from 'react-router-dom'
import './App.css'
import NewParty from './routes/NewParty'

function App() {


  return (
    <div>
      <Outlet/>
      <Home/>
      
    </div>
  )
}

export default App
