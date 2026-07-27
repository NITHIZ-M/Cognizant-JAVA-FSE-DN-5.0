function App() {
  const heading = <h1>Office Space Rental</h1>
  const imgUrl = "https://via.placeholder.com/300"

  const office = {
    name: "Prime Work Hub",
    rent: 55000,
    address: "MG Road, Bangalore"
  }

  const officeList = [
    { name: "Startup Space", rent: 45000, address: "HSR Layout" },
    { name: "Enterprise Hub", rent: 65000, address: "Whitefield" },
    { name: "TechNest", rent: 70000, address: "Electronic City" },
    { name: "Workbay", rent: 30000, address: "Koramangala" }
  ]

  const getRentStyle = rent => ({
    color: rent < 60000 ? "red" : "green"
  })

  return (
    <div>
      {heading}
      <img src={imgUrl} alt="office" />
      <h2>{office.name}</h2>
      <p style={getRentStyle(office.rent)}>Rent: {office.rent}</p>
      <p>Address: {office.address}</p>

      <h3>All Office Listings</h3>
      {officeList.map((o, i) => (
        <div key={i}>
          <h4>{o.name}</h4>
          <p style={getRentStyle(o.rent)}>Rent: {o.rent}</p>
          <p>Address: {o.address}</p>
        </div>
      ))}
    </div>
  )
}

export default App;
