import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';  

import './Hero.css';

const Hero = () => {
  const navigate = useNavigate();
  const [showPopup, setShowPopup] = useState(false);  
  const handleRegisterClick = () => {
    setShowPopup(true);  
  };

  const handleChoice = (role) => {
    setShowPopup(false);  
    if (role === 'student') {
      navigate('/student-register');  
    } else if (role === 'teacher') {
      navigate('/teacher-register'); 
    }
  };

  return (
    <section className="hero">
      <div className="hero-content">
        <h2>Aprenda inglês de forma rápida e eficaz</h2>
        <p>Melhore seu inglês com cursos online de qualidade e professores nativos.</p>
        <button className="cta-btn" onClick={handleRegisterClick}>Matricule-se Agora</button>
      </div>

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
