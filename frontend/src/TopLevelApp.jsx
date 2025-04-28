import { useState } from 'react';
import './App.css';
import App from './App.jsx';
import Login from './Login.jsx';

export default function TopLevelApp() {
  const [activePage, setActivePage] = useState('home');

  return (
    <>
      <nav className="navbar">
        <div className="navbar-logo">MyApp</div>
        <div>
          <a href="#" onClick={() => setActivePage('home')}> Home |</a>
          <a href="#" onClick={() => setActivePage('about')}> About |</a>
          <a href="#" onClick={() => setActivePage('contact')}> Contact |</a>
        </div>
      </nav>
      <div className="container">
        <main className="main-content">
          {activePage === 'home' && (
            <>
              <h1>Welcome Home</h1>
              <App />
            </>
          )}
          {activePage === 'about' && (
            <>
              <Login />
            </>
          )}
          {activePage === 'contact' && <h1>Contact</h1>}
        </main>
      </div>
    </>
  );
}


