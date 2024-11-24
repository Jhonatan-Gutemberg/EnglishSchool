import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './components/home/Home';
import Activities from './components/page_activities/Activities';
import Page_activity from './components/page_activities/Page_activity';
import './App.css';

const App = () => {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/classroom/:turmaId/activities" element={<Page_activity />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
