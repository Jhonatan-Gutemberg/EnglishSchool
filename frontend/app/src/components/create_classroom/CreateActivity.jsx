import React, { useState, useEffect } from "react";
import "./Styles.css";

const CreateActivity = ({ onActivityCreated }) => {
  const [activityData, setActivityData] = useState({
    name: "",
    description: "",
    difficultyLevel: 0,
    id_classroom: 0,
  });

  const [classrooms, setClassrooms] = useState([]);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const getToken = () => {
    return localStorage.getItem("auth-token");
  };

  useEffect(() => {
    const fetchClassrooms = async () => {
      setIsLoading(true);
      try {
        const response = await fetch("http://localhost:8080/classroom/all");
        if (!response.ok) {
          throw new Error("Erro ao carregar turmas");
        }
        const data = await response.json();
        setClassrooms(data.data);
      } catch (err) {
        setError(err.message);
      } finally {
        setIsLoading(false);
      }
    };

    fetchClassrooms();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setActivityData({ ...activityData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setSuccess("");
    setIsLoading(true);

    const token = getToken();
    if (!token) {
      setError("Você precisa estar logado para criar uma atividade.");
      setIsLoading(false);
      return;
    }

    const currentDate = new Date().toISOString().split("T")[0]; 
    const activityWithDate = { ...activityData, dueDate: currentDate };

    try {
      const response = await fetch("http://localhost:8080/activity/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`,
        },
        body: JSON.stringify(activityWithDate),
      });

      if (!response.ok) {
        throw new Error("Erro ao criar atividade");
      }

      setSuccess("Atividade criada com sucesso!");
      setActivityData({
        name: "",
        description: "",
        difficultyLevel: 0,
        id_classroom: 0,
      });

      if (onActivityCreated) onActivityCreated();
    } catch (err) {
      setError(err.message);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="create-activity">
      <h3>Criar Atividade</h3>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="name">Nome</label>
          <input
            type="text"
            id="name"
            name="name"
            value={activityData.name}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="description">Descrição</label>
          <textarea
            id="description"
            name="description"
            value={activityData.description}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="difficultyLevel">Nível de Dificuldade</label>
          <input
            type="number"
            id="difficultyLevel"
            name="difficultyLevel"
            value={activityData.difficultyLevel}
            onChange={handleChange}
            min="0"
            max="5"
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="id_classroom">Turma</label>
          <select
            id="id_classroom"
            name="id_classroom"
            value={activityData.id_classroom}
            onChange={handleChange}
            required
          >
            <option value="">Selecione uma turma</option>
            {classrooms.map((classroom) => (
              <option key={classroom.id} value={classroom.id}>
                {classroom.name}
              </option>
            ))}
          </select>
        </div>

        {error && <p className="error-message">{error}</p>}
        {success && <p className="success-message">{success}</p>}
        {isLoading && <p>Carregando...</p>}

        <button type="submit" disabled={isLoading}>
          {isLoading ? "Criando..." : "Criar Atividade"}
        </button>
      </form>
    </div>
  );
};

export default CreateActivity;
