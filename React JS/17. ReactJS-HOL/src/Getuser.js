import { Component } from 'react'

class Getuser extends Component {
  constructor() {
    super()
    this.state = {
      user: null
    }
  }

  async componentDidMount() {
    const res = await fetch('https://api.randomuser.me/')
    const data = await res.json()
    this.setState({ user: data.results[0] })
  }

  render() {
    const { user } = this.state
    return (
      <div>
        <h2>User Info</h2>
        {user && (
          <div>
            <p>Title: {user.name.title}</p>
            <p>First Name: {user.name.first}</p>
            <img src={user.picture.large} alt="user" />
          </div>
        )}
      </div>
    )
  }
}

export default Getuser
