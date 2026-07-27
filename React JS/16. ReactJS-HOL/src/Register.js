import { useState } from 'react'

function Register() {
  const [name, setName] = useState("")
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [error, setError] = useState("")

  const validate = () => {
    if (name.length < 5) return "Name must be at least 5 characters"
    if (!email.includes("@") || !email.includes(".")) return "Invalid email format"
    if (password.length < 8) return "Password must be at least 8 characters"
    return ""
  }

  const handleSubmit = e => {
    e.preventDefault()
    const err = validate()
    if (err) {
      setError(err)
    } else {
      alert("Registered Successfully")
      setError("")
      setName("")
      setEmail("")
      setPassword("")
    }
  }

  return (
    <form onSubmit={handleSubmit}>
      <h2>Register</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <input
        type="text"
        placeholder="Name"
        value={name}
        onChange={e => setName(e.target.value)}
      />
      <br /><br />
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={e => setEmail(e.target.value)}
      />
      <br /><br />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={e => setPassword(e.target.value)}
      />
      <br /><br />
      <button type="submit">Register</button>
    </form>
  )
}

export default Register
