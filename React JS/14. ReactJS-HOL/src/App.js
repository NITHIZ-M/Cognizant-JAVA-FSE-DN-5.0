import { useState } from 'react'
import ThemeContext from './ThemeContext'
import EmployeesList from './EmployeesList'

function App() {
  const [theme, setTheme] = useState('light')

  return (
    <ThemeContext.Provider value={theme}>
      <div>
        <button onClick={() => setTheme('light')}>Light</button>
        <button onClick={() => setTheme('dark')}>Dark</button>
        <EmployeesList />
      </div>
    </ThemeContext.Provider>
  )
}

export default App
