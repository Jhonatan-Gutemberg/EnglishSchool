import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';  // Importando useNavigate

import './Hero.css';

const Hero = () => {
  const navigate = useNavigate();  // Usando useNavigate ao invés de useHistory
  const [showPopup, setShowPopup] = useState(false);  // Controla a exibição do popup

  const handleRegisterClick = () => {
    setShowPopup(true);  // Abre o popup ao clicar no botão
  };

  const handleChoice = (role) => {
    setShowPopup(false);  // Fecha o popup
    if (role === 'student') {
      navigate('/student-register');  // Navega para o formulário de cadastro de estudante
    } else if (role === 'teacher') {
      navigate('/teacher-register');  // Navega para o formulário de cadastro de professor
    }
  };

  return (
    <section className="hero">
      <div className="hero-content">
        <h2>Aprenda inglês de forma rápida e eficaz</h2>
        <p>Melhore seu inglês com cursos online de qualidade e professores nativos.</p>
        <button className="cta-btn" onClick={handleRegisterClick}>Matricule-se Agora</button>
      </div>

      {/* Popup de escolha de registro */}
      {showPopup && (
        <div className="popup">
          <div className="popup-content">
            <h3>Escolha o tipo de usuário:</h3>
            <button onClick={() => handleChoice('student')}>Estudante</button>
            <button onClick={() => handleChoice('teacher')}>Professor</button>
          </div>
        </div>
      )}
    </section>
  );
};

export default Hero;
