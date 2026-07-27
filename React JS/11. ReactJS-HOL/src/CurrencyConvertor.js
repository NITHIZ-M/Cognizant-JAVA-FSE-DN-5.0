import { useState } from 'react'

function CurrencyConvertor() {
  const [inr, setInr] = useState("")
  const [euro, setEuro] = useState("")

  const handleSubmit = () => {
    const converted = (parseFloat(inr) / 90).toFixed(2)
    setEuro(converted)
  }

  return (
    <div>
      <h3>Currency Convertor</h3>
      <input
        type="number"
        value={inr}
        onChange={e => setInr(e.target.value)}
        placeholder="Enter INR"
      />
      <button onClick={handleSubmit}>Convert</button>
      <div>Euro: {euro}</div>
    </div>
  )
}

export default CurrencyConvertor
