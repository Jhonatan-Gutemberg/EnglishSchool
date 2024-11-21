import React from 'react';
import './Home.css';
import Cursos from './Classroom/Cursos';

const Home = () => {
  return (
    <div className="home-container">
      <header className="header">
        <div className="logo">
          <h1>Escola de Inglês</h1>
        </div>
        <nav className="nav">
          <ul>
            <li><a href="#cursos">Cursos</a></li>
            <li><a href="#sobre">Sobre</a></li>
            <li><a href="#contato">Contato</a></li>
            <li><button className="btn-login">Login</button></li>
          </ul>
        </nav>
      </header>

      <section className="hero">
        <div className="hero-content">
          <h2>Aprenda inglês de forma rápida e eficaz</h2>
          <p>Melhore seu inglês com cursos online de qualidade e professores nativos.</p>
          <button className="cta-btn">Matricule-se Agora</button>
        </div>
      </section>

      <section id="cursos" className="courses">
        <h2>Oferecemos os seguintes cursos</h2>
        <Cursos />
      </section>

      <section className="benefits">
        <h2>Por que escolher nossa escola?</h2>
        <ul>
          <li>Aulas ao vivo com professores nativos</li>
          <li>Flexibilidade de horários</li>
          <li>Certificado reconhecido</li>
          <li>Material didático atualizado</li>
        </ul>
      </section>

      <section className="testimonials">
        <h2>O que nossos alunos dizem</h2>
        <div className="testimonial">
          <p>"A melhor escola de inglês que já estudei! Professores excelentes e aulas dinâmicas!"</p>
          <span>- João Silva</span>
        </div>
        <div className="testimonial">
          <p>"Aprendi muito rápido com a metodologia da escola. Super recomendo!"</p>
          <span>- Maria Oliveira</span>
        </div>
      </section>

      <footer id="contato" className="footer">
        <p>&copy; 2024 Escola de Inglês | Todos os direitos reservados</p>
        <div className="social-links">
          <a href="https://facebook.com" target="_blank" rel="noopener noreferrer">Facebook</a>
          <a href="https://instagram.com" target="_blank" rel="noopener noreferrer">Instagram</a>
        </div>
      </footer>
    </div>
  );
};

export default Home;
