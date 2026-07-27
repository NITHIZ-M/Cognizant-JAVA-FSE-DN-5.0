function IndianPlayers() {
  const players = ['P1', 'P2', 'P3', 'P4', 'P5', 'P6', 'P7', 'P8'];
  const odd = players.filter((_, i) => i % 2 === 0);
  const even = players.filter((_, i) => i % 2 !== 0);
  const T20 = ['T1', 'T2', 'T3'];
  const Ranji = ['R1', 'R2', 'R3'];
  const merged = [...T20, ...Ranji];

  return (
    <div>
      <h2>Odd Team Players</h2>
      {odd.map((p, i) => <div key={i}>{p}</div>)}
      <h2>Even Team Players</h2>
      {even.map((p, i) => <div key={i}>{p}</div>)}
      <h2>Merged Team</h2>
      {merged.map((p, i) => <div key={i}>{p}</div>)}
    </div>
  );
}

export default IndianPlayers;
