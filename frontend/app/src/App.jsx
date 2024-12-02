import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";

import Home from "./components/home/Home";
import Activities from "./components/page_activities/Activities";
import Page_activity from "./components/page_activities/Page_activity";
import PageRegister from "./components/page_register/Page_register";
import TeacherRegister from "./components/page_register/TeacherRegister";
import StudentRegister from "./components/page_register/StudentRegister";
import TeacherDashboard from "./components/create_classroom/TeacherDashBoard";
import Login from "./components/login/Login";
import ShowClassroom from "./components/create_classroom/ShowClassroom";

import "./App.css";

const isAuthenticated = () => {
  return localStorage.getItem("auth-token") !== null;
};

const ProtectedRoute = ({ children }) => {
  return isAuthenticated() ? children : <Navigate to="/login" />;
};

const App = () => {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route
            path="/activities"
            element={<Page_activity />}
          />
          <Route path="/register" element={<PageRegister />} />
          <Route path="/teacher-register" element={<TeacherRegister />} />
          <Route path="/student-register" element={<StudentRegister />} />


          <Route
            path="/dashboard"
            element={
              <ProtectedRoute>
                <TeacherDashboard />
              </ProtectedRoute>
            }
          />


          <Route
            path="/teacher/:teacherId/classrooms"
            element={
              <ProtectedRoute>
                <ShowClassroom />
              </ProtectedRoute>
            }
          />

          <Route path="/login" element={<Login />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
