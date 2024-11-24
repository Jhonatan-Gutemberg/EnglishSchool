import React from 'react';
import TeacherRegister from './TeacherRegister'; 
import Footer from '../footer/Footer'; 
import './PageRegister.css';

const Page_register = () => {
  return (
    <div className="page-register">
      <h2>Cadastro de Professor</h2>

      <TeacherRegister />
      <Footer />
    </div>
  );
};

export default Page_register;
