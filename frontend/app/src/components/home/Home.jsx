// src/components/Home.js
import React from 'react';
import './Home.css';
import Header from '../header/Header';
import Hero from './Hero';
import Cursos from './Classroom/Cursos';
import Footer from '../footer/Footer';

const Home = () => {
  return (
    <div className="home-container">
      <Header />
      <Hero />
      <section id="cursos" className="courses">
        <h2>Oferecemos os seguintes cursos</h2>
        <Cursos />
      </section>
      <Footer />
    </div>
  );
};

export default Home;
