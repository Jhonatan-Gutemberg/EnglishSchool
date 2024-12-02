import React, { useState } from 'react';
import "../login/Login.css";

const Login = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState('');

  const handleLogin = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    setErrorMessage('');
  
    try {
      const response = await fetch('http://localhost:8080/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password }),
      });
  
      const data = await response.json();
  
      if (response.ok && data.data) {
        const token = data.token;
        const userType = data.data.type;
        const userId = data.data.id;
        const name = data.data.name;
  
        localStorage.setItem('auth-token', token);
        localStorage.setItem('auth-id', userId);
  
        alert(`Bem-vindo, ${name}!`);
  
        if (userType === 'TEACHER') {
          window.location.href = '/dashboard';
        } else if (userType === 'STUDENT') {
          window.location.href = '/activities';
        }
      } else {
        setErrorMessage(data.message || 'Erro no login.');
      }
    } catch (error) {
      setErrorMessage('Erro na conexão com o servidor.');
      console.error(error);
    } finally {
      setIsLoading(false);
    }
  };
  

  return (
    <div className="login-container">
      <h2>Login</h2>
      <form onSubmit={handleLogin}>
        <div className="input-group">
          <label htmlFor="email">E-mail</label>
          <input
            type="email"
            id="email"
            placeholder="Digite seu e-mail"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div className="input-group">
          <label htmlFor="password">Senha</label>
          <input
            type="password"
            id="password"
            placeholder="Digite sua senha"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit" disabled={isLoading}>
          {isLoading ? 'Carregando...' : 'Entrar'}
        </button>
      </form>
      {errorMessage && <p className="error-message">{errorMessage}</p>}
    </div>
  );
};

export default Login;
