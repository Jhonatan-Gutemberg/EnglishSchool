import React, { useState } from "react";
import { FaPlus } from "react-icons/fa";
import "./CreateClass.css";

const CreateClassroom = ({ teacherId, onClassCreated }) => {
  const [className, setClassName] = useState("");
  const [isCreating, setIsCreating] = useState(false);

  const handleCreateClass = async () => {
    if (!className.trim()) {
      alert("Por favor, insira o nome da turma.");
      return;
    }

    setIsCreating(true); 

    try {
      const response = await fetch(`http://localhost:8080/classroom/register/${teacherId}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ name: className }),
      });

      if (response.ok) {
        alert("Turma criada com sucesso!");
        setClassName("");
        if (onClassCreated) onClassCreated();
      } else {
        alert("Erro ao criar turma.");
      }
    } catch (error) {
      console.error("Erro na requisição:", error);
      alert("Erro ao criar turma. Verifique a conexão com o servidor.");
    } finally {
      setIsCreating(false); 
    }
  };

  return (
    <div className="create-classroom-card">
      <h2>Criar Nova Turma</h2>
      <p>Organize suas aulas criando uma nova turma com facilidade.</p>
      <div className="input-container">
        <input
          type="text"
          placeholder="Digite o nome da turma"
          value={className}
          onChange={(e) => setClassName(e.target.value)}
        />
        <button onClick={handleCreateClass} disabled={isCreating} className="create-button">
          {isCreating ? "Criando..." : <><FaPlus /> Criar Turma</>}
        </button>
      </div>
    </div>
  );
};

export default CreateClassroom;
