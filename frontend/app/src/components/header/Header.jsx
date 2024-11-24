import React from 'react';
import './Header.css';

const Header = () => {
  return (
    <header className="header">
      <div className="header-container">
        <div className="logo">
          <h1>EnglishSchool</h1>
        </div>
        <div>

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
          <button className="login-button">Login</button>
        </div>
      </div>
    </header>
  );
};

export default Header;
