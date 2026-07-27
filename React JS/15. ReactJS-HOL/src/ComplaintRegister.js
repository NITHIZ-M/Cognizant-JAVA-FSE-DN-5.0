import { useState } from 'react'

function ComplaintRegister() {
  const [name, setName] = useState("")
  const [complaint, setComplaint] = useState("")

  const handleSubmit = e => {
    e.preventDefault()
    const refNo = Math.floor(100000 + Math.random() * 900000)
    alert(`Complaint submitted!\nName: ${name}\nReference No: ${refNo}`)
    setName("")
    setComplaint("")
  }

  return (
    <form onSubmit={handleSubmit}>
      <h2>Complaint Register</h2>
      <input
        type="text"
        placeholder="Employee Name"
        value={name}
        onChange={e => setName(e.target.value)}
      />
      <br /><br />
      <textarea
        placeholder="Enter your complaint"
        value={complaint}
        onChange={e => setComplaint(e.target.value)}
      />
      <br /><br />
      <button type="submit">Submit Complaint</button>
    </form>
  )
}

export default ComplaintRegister
