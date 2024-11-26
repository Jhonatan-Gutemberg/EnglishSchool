import React from 'react';
import { useNavigate } from 'react-router-dom'; 
import './Header.css';

const Header = () => {
  const navigate = useNavigate(); 

  const handleLogin = () => {
    navigate('/dashboard');
  };

  return (
    <header className="header">
      <div className="header-container">
        <div className="logo">
          <h1>EnglishSchool</h1>
        </div>
        <nav className="nav-links">
          <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/activities">Atividades</a></li>
            <li><a href="/profile">Perfil</a></li>
            <li><a href="/about">Sobre</a></li>
          </ul>
        </nav>
        <div className="auth-buttons">
          <button className="login-button" onClick={handleLogin}>
            Login
          </button>
        </div>
      </div>
    </header>
  );
};

export default Header;
