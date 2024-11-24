import React from 'react';
import Header from '../header/Header';  
import Footer from '../footer/Footer';  
import Activities from '../page_activities/Activities';  


const Page_activity = ({ children }) => {
  return (
    <div className="layout">
      <Header /> 
      <Activities />
      <Footer />  
    </div>
  );
};

export default Page_activity;
