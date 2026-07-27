function ListofPlayers() {
  const players = [
    { name: 'Player 1', score: 90 },
    { name: 'Player 2', score: 45 },
    { name: 'Player 3', score: 76 },
    { name: 'Player 4', score: 30 },
    { name: 'Player 5', score: 88 },
    { name: 'Player 6', score: 95 },
    { name: 'Player 7', score: 60 },
    { name: 'Player 8', score: 72 },
    { name: 'Player 9', score: 50 },
    { name: 'Player 10', score: 40 },
    { name: 'Player 11', score: 81 }
  ];

  const filtered = players.filter(p => p.score < 70);

  return (
    <div>
      <h2>All Players</h2>
      {players.map((p, i) => (
        <div key={i}>{p.name} - {p.score}</div>
      ))}
      <h3>Filtered (Score < 70)</h3>
      {filtered.map((p, i) => (
        <div key={i}>{p.name} - {p.score}</div>
      ))}
    </div>
  );
}

export default ListofPlayers;
