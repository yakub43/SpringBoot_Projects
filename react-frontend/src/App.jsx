import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import EmployeeComponent from './component/EmployeeComponent'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className='container'>
     <EmployeeComponent></EmployeeComponent>
    </div>
  )
}

export default App
