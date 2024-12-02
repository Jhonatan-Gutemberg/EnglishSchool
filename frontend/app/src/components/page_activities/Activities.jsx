import React, { useState, useEffect } from 'react';
import './Activities.css';
import Alert from './alert/Alert';

const Activities = () => {
  const [activities, setActivities] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [alert, setAlert] = useState(null);

  const token = localStorage.getItem('auth-token');
  const id_classroom = localStorage.getItem('id-classroom');
  const studentId = localStorage.getItem('student-id');  

  useEffect(() => {
    const loadActivities = async () => {
      if (!token) {
        setError('Token de autenticação não encontrado.');
        setLoading(false);
        return;
      }
  
      if (!id_classroom) {
        setError('Classroom ID não encontrado');
        setLoading(false);
        return;
      }
  
      try {
        const response = await fetch(`http://localhost:8080/classroom/1/activities`, {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
          },
        });
  
        if (!response.ok) {
          throw new Error('Erro ao carregar atividades');
        }
  
        const data = await response.json();
        if (data.success) {
          const activitiesWithStatus = data.data.map((activity) => ({
            ...activity,
            status: 'Pendente',
          }));
  
      
          for (const activity of activitiesWithStatus) {
            try {
              const res = await fetch(`http://localhost:8080/activity/${studentId}/${activity.id}/status`, {
                method: 'GET',
                headers: {
                  'Authorization': `Bearer ${token}`,
                },
              });
  
              if (!res.ok) throw new Error('Erro ao buscar status da atividade');
              const status = await res.text();
              activity.status = status === 'COMPLETED' ? 'Concluída' : 'Pendente';
            } catch {
              activity.status = 'Pendente';
            }
          }
  
          setActivities(activitiesWithStatus);
        } else {
          setError('Erro ao carregar atividades');
        }
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };
  
    loadActivities();
  }, [token, id_classroom, studentId]);
  

  const handleConfirmActivity = (activityId) => {
    const requestBody = {
      studentId: studentId,  
      activityId: activityId,
    };

    fetch('http://localhost:8080/activity/confirmCompletion', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`,
      },
      body: JSON.stringify(requestBody),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Erro ao confirmar a conclusão da atividade');
        }
        return response.json();
      })
      .then((data) => {
        if (data.success) {
          setActivities((prevActivities) =>
            prevActivities.map((activity) =>
              activity.id === activityId
                ? { ...activity, status: 'Concluída' }
                : activity
            )
          );
          setAlert({ type: 'success', message: 'Atividade concluída com sucesso!' });
        } else {
          setAlert({ type: 'error', message: 'Falha ao confirmar a conclusão da atividade.' });
        }
      })
      .catch((err) => {
        setAlert({ type: 'error', message: 'Erro: ' + err.message });
      });
  };

  const closeAlert = () => {
    setAlert(null);
  };

  if (loading) {
    return <div className="loading">Carregando atividades...</div>;
  }

  if (error) {
    return <div className="error">Erro: {error}</div>;
  }

  return (
    <div className="activities-list">
      <h2 className="activities-title">Atividades da Aula</h2>

      {alert && <Alert type={alert.type} message={alert.message} onClose={closeAlert} />}

      {activities.map((activity) => (
        <div key={activity.id} className="activity-block">
          <div className="activity-header">
            <h3>{activity.name}</h3>
            <span className="activity-date">
              Criado em: {activity.createdDate}
            </span>
          </div>
          <p className="activity-description">{activity.description}</p>

          <div className="status">
            <label><strong>Status:</strong> {activity.status}</label>
          </div>

          {activity.status !== 'Concluída' && (
            <button
              className="confirm-button"
              onClick={() => handleConfirmActivity(activity.id)}
            >
              Confirmar Conclusão
            </button>
          )}
        </div>
      ))}
    </div>
  );
};

export default Activities;
