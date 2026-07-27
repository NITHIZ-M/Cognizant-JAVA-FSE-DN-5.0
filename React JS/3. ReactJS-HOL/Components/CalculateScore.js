// src/Components/CalculateScore.js
import React from 'react';

const CalculateScore = ({ name, school, total, goal }) => {
  // Simple average calculation
  const average = total / goal;

  return (
    <div style={{ border: '1px solid gray', padding: '20px', margin: '20px', borderRadius: '10px' }}>
      <h2>Student Score Details</h2>
      <p><strong>Name:</strong> {name}</p>
      <p><strong>School:</strong> {school}</p>
      <p><strong>Total Marks:</strong> {total}</p>
      <p><strong>Goal Subjects:</strong> {goal}</p>
      <p><strong>Average Score:</strong> {average.toFixed(2)}</p>
    </div>
  );
};

export default CalculateScore;
