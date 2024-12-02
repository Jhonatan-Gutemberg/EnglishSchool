import React, { useState, useEffect } from "react";
import { FaPlus } from "react-icons/fa";

const CreateClassroom = ({ onClassCreated }) => {
  const [className, setClassName] = useState("");
  const [isCreating, setIsCreating] = useState(false);
  const [teacherId, setTeacherId] = useState(null);
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    const storedTeacherId = localStorage.getItem("auth-id");
    if (storedTeacherId) {
      setTeacherId(storedTeacherId); 
    } else {
      setErrorMessage("ID do professor não encontrado.");
    }
  }, []);

  const handleCreateClass = async () => {
    if (!className.trim()) {
      setErrorMessage("Por favor, insira o nome da turma.");
      return;
    }

    if (!teacherId) {
      setErrorMessage("ID do professor não encontrado.");
      return;
    }

    setIsCreating(true);
    setErrorMessage(""); 

    const token = localStorage.getItem("auth-token");
    if (!token) {
      setErrorMessage("Token de autenticação não encontrado.");
      setIsCreating(false);
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/classroom/register/${teacherId}`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`,
        },
        body: JSON.stringify({ name: className }),
      });

      if (response.ok) {
        alert("Turma criada com sucesso!");
        setClassName("");
        if (onClassCreated) onClassCreated();
      } else {
        setErrorMessage("Erro ao criar turma. Verifique os dados.");
      }
    } catch (error) {
      console.error("Erro na requisição:", error);
      setErrorMessage("Erro ao criar turma. Verifique a conexão com o servidor.");
    } finally {
      setIsCreating(false);
    }
  };

  return (
    <div className="create-classroom-card">
      <h2>Criar Nova Turma</h2>
      <p>Organize suas aulas criando uma nova turma com facilidade.</p>
      {errorMessage && <p className="error-message">{errorMessage}</p>} 
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
