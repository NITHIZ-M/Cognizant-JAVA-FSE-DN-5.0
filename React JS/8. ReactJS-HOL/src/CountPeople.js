import React, { Component } from 'react';

class CountPeople extends Component {
  constructor(props) {
    super(props);

    // Initialize state
    this.state = {
      entryCount: 0,
      exitCount: 0
    };
  }

  // Method to update entry count
  updateEntry = () => {
    this.setState((prevState) => ({
      entryCount: prevState.entryCount + 1
    }));
  };

  // Method to update exit count
  updateExit = () => {
    this.setState((prevState) => ({
      exitCount: prevState.exitCount + 1
    }));
  };

  render() {
    return (
      <div style={{ padding: '30px', fontFamily: 'Arial', textAlign: 'center' }}>
        <h2>People Counter</h2>
        <p><strong>Number of people entered:</strong> {this.state.entryCount}</p>
        <p><strong>Number of people exited:</strong> {this.state.exitCount}</p>

        <button onClick={this.updateEntry} style={{ marginRight: '10px', padding: '10px 20px' }}>
          Login
        </button>
        <button onClick={this.updateExit} style={{ padding: '10px 20px' }}>
          Exit
        </button>
      </div>
    );
  }
}

export default CountPeople;
