import "./App.css"
import { useState, useEffect, useRef } from 'react';

function Square({value, onSquareClick}) {
  return <button className="square" onClick={onSquareClick}>{value}</button>;
}

function Board() {
  const [squares, setSquares] = useState(Array(9).fill(null));
  const [status, setStatus] = useState("");
  const [currentGameId, setCurrentGameId] = useState(null);
  const [currentPlayerId, setCurrentPlayerId] = useState(null);

  const socketRef = useRef(null);

  useEffect( () => {
    const socket = new WebSocket("ws://localhost:8080/game");
    socketRef.current = socket;

    socket.onopen = () => {
      console.log('Websocket connected to localhost:8080/game');
    };

    socket.onmessage = (event) => {
      const gameState = JSON.parse(event.data);

      if (gameState.error) {
        alert(gameState.error);
      } else {
        console.log(gameState);
        setSquares(gameState.board.flat());
        setStatus(gameState.currentTurnPlayerId === currentPlayerId ? "Your turn!" : "Opponent's turn.");
        setCurrentGameId(gameState.gameId);
        setCurrentPlayerId(gameState.currentPlayerId);
      }
    };

    socket.onclose = () => {
      console.log('Websocket diconnected');
    }

    return () => socket.close();
    
  }, [currentPlayerId]);
  
  function sendMove(gameId, playerId, row, col) {
    const move = {
      gameId,
      playerId,
      row,
      col,
    };
    socketRef.current.sned(JSON.stringify(move));
  }

  function handleClick(squareIndex) {
    if (!currentGameId || !currentPlayerId) {
      alert("Game hasn't started yet or player ID is invalid!");
      return;
    }

    const row = Math.floow(squareIndex / 3);
    const col = squareIndex % 3;

    sendMove(currentGameId, currentPlayerId, row, col);
  }

  return (
    <div>
      <div class="board-div">
        <div className="status">{status}</div>
        <div className="board-row">
            <Square value={squares[0]} onSquareClick={() => handleClick(0)}/>
            <Square value={squares[1]} onSquareClick={() => handleClick(1)}/>
            <Square value={squares[2]} onSquareClick={() => handleClick(2)}/>
        </div>
        <div className="board-row">
            <Square value={squares[3]} onSquareClick={() => handleClick(3)}/>
            <Square value={squares[4]} onSquareClick={() => handleClick(4)}/>
            <Square value={squares[5]} onSquareClick={() => handleClick(5)}/>
        </div>
        <div className="board-row">
            <Square value={squares[6]} onSquareClick={() => handleClick(6)}/>
            <Square value={squares[7]} onSquareClick={() => handleClick(7)}/>
            <Square value={squares[8]} onSquareClick={() => handleClick(8)}/>
        </div>
      </div>
    </div>
  ) 
}

export default function BoardContainer() {
  return (
    <>
      <Board />
    </>
  );
}

