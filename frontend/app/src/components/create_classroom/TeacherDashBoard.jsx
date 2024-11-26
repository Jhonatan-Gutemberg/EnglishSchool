import React, { useState } from "react";
import { FaHome, FaChalkboardTeacher, FaCog } from "react-icons/fa";
import CreateClassroom from "./CreateClass";
import ShowClassroom from "./ShowClassroom";
import "./Styles.css";

const TeacherDashboard = ({ teacherId }) => {
  const [update, setUpdate] = useState(false);

  const refreshClasses = () => {
    setUpdate(!update);
  };

  return (
    <div className="dashboard">
      <aside className="sidebar">
        <h2>Menu</h2>
        <ul>
          <li>
            <FaHome className="icon" /> Home
          </li>
          <li>
            <FaChalkboardTeacher className="icon" /> Minhas Turmas
          </li>
          <li>
            <FaCog className="icon" /> Configurações
          </li>
        </ul>
      </aside>

      <main className="main-content">
        <header className="header">
          <h1>Bem-vindo, Professor</h1>
        </header>
        <section className="content">
          <div className="card">
            <CreateClassroom teacherId={teacherId} onClassCreated={refreshClasses} />
          </div>
          <div className="card">
            <ShowClassroom key={update} />
          </div>
        </section>
      </main>
    </div>
  );
};

export default TeacherDashboard;
