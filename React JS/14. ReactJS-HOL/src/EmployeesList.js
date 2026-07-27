import EmployeeCard from './EmployeeCard'

function EmployeesList() {
  const employees = [
    { id: 1, name: 'John', role: 'Developer' },
    { id: 2, name: 'Jane', role: 'Designer' }
  ]

  return (
    <div>
      {employees.map(emp => (
        <EmployeeCard key={emp.id} employee={emp} />
      ))}
    </div>
  )
}

export default EmployeesList
