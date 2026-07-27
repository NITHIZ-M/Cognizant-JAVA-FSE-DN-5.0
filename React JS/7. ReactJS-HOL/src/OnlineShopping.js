import React, { Component } from 'react';
import Cart from './Cart';

class OnlineShopping extends Component {
  constructor(props) {
    super(props);

    // Create an array of 5 cart items
    this.state = {
      items: [
        new Cart('Laptop', 75000),
        new Cart('Smartphone', 30000),
        new Cart('Headphones', 1500),
        new Cart('Shoes', 2000),
        new Cart('Backpack', 1200),
      ]
    };
  }

  render() {
    return (
      <div style={{ padding: '20px' }}>
        <h2>Online Shopping Cart</h2>
        <ul>
          {this.state.items.map((item, index) => (
            <li key={index}>
              <strong>Item:</strong> {item.itemname} <br />
              <strong>Price:</strong> ₹{item.price}
            </li>
          ))}
        </ul>
      </div>
    );
  }
}

export default OnlineShopping;
