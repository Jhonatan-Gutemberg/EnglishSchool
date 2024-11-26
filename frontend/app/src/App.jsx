import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Home from "./components/home/Home";
import Activities from "./components/page_activities/Activities";
import Page_activity from "./components/page_activities/Page_activity";
import PageRegister from "./components/page_register/Page_register";
import TeacherRegister from "./components/page_register/TeacherRegister";
import StudentRegister from "./components/page_register/StudentRegister";
import TeacherDashboard from "./components/create_classroom/TeacherDashBoard";

import "./App.css";

const App = () => {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route
            path="/classroom/:turmaId/activities"
            element={<Page_activity />}
          />
          <Route path="/register" element={<PageRegister />} />
          <Route path="/teacher-register" element={<TeacherRegister />} />
          <Route path="/student-register" element={<StudentRegister />} />
          <Route path="/dashboard" element={<TeacherDashboard />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
