import React, { useState, useEffect } from 'react';

const Cursos = () => {
  const [cursos, setCursos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchCursos = async () => {
    try {
      const response = await fetch('http://localhost:8080/classroom/all');
      const data = await response.json();

      console.log('Resposta da API:', data); 

     
      if (data && Array.isArray(data)) {
        setCursos(data); 
      } else if (data && Array.isArray(data.data)) {
        setCursos(data.data); 
      } else {
        setCursos([]); 
      }
    } catch (err) {
      setError('Erro ao carregar as turmas');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchCursos();
  }, []);

  if (loading) return <p>Carregando turmas...</p>;
  if (error) return <p>{error}</p>;

  return (
    <div className="course-list">
      {cursos.length === 0 ? (
        <p>Nenhuma turma disponível no momento.</p>
      ) : (
        cursos.map((curso) => (
          <div key={curso.id} className="course-item">
            <h3>{curso.name}</h3>
            <p>
              Professor: {curso.teacher ? curso.teacher.name : 'Sem professor designado'}
            </p>
          </div>
        ))
      )}
    </div>
  );
};

export default Cursos;
