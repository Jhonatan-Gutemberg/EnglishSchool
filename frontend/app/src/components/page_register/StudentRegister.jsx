import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom'; 
import Alert from '../page_activities/alert/Alert'; 
import './StudentRegister.css';  

const StudentRegister = () => {
  const navigate = useNavigate(); 
  const [formData, setFormData] = useState({
    name: '',
    databirth: '',
    email: '',
    phoneNumber: '',
    password: '',
    address: '',
    cpf: '',
    rg: '',
    level: '',
    id_classroom: 0,  
  });

  const [classrooms, setClassrooms] = useState([]); 
  const [error, setError] = useState('');  
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [alert, setAlert] = useState(null); 

  useEffect(() => {
    const fetchClassrooms = async () => {
        try {
          const response = await fetch('http://localhost:8080/classroom/all');
          if (!response.ok) {
            throw new Error('Erro ao carregar turmas');
          }
          const result = await response.json();
          
         
          if (Array.isArray(result.data)) {
            setClassrooms(result.data);  
          } else {
            setClassrooms([]); 
          }
        } catch (err) {
          setError('Falha ao carregar turmas');
        }
      };
      

    fetchClassrooms();
  }, []); 

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    setIsSubmitting(true);
    setError('');
    setAlert(null); 

    try {
      const response = await fetch('http://localhost:8080/student/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || 'Erro ao cadastrar o aluno');
      }

      const result = await response.json();
      setAlert({ type: 'success', message: 'Cadastro realizado com sucesso!' });

      setTimeout(() => {
        navigate('/'); 
      }, 2000);  

    } catch (err) {
      setError(err.message);
      setAlert({ type: 'error', message: err.message });
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="student-register">
      <h2>Cadastro de Aluno</h2>

      {alert && <Alert type={alert.type} message={alert.message} onClose={() => setAlert(null)} />}

      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="name">Nome</label>
          <input
            type="text"
            id="name"
            name="name"
            value={formData.name}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="databirth">Data de Nascimento</label>
          <input
            type="date"
            id="databirth"
            name="databirth"
            value={formData.databirth}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="email">Email</label>
          <input
            type="email"
            id="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="phoneNumber">Telefone</label>
          <input
            type="text"
            id="phoneNumber"
            name="phoneNumber"
            value={formData.phoneNumber}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="password">Senha</label>
          <input
            type="password"
            id="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="address">Endereço</label>
          <input
            type="text"
            id="address"
            name="address"
            value={formData.address}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="cpf">CPF</label>
          <input
            type="text"
            id="cpf"
            name="cpf"
            value={formData.cpf}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="rg">RG</label>
          <input
            type="text"
            id="rg"
            name="rg"
            value={formData.rg}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="level">Nível</label>
          <input
            type="text"
            id="level"
            name="level"
            value={formData.level}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="id_classroom">Turma</label>
          <select
            id="id_classroom"
            name="id_classroom"
            value={formData.id_classroom}
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

        <div className="form-group">
          <button type="submit" disabled={isSubmitting}>
            {isSubmitting ? 'Cadastrando...' : 'Cadastrar'}
          </button>
        </div>
      </form>
    </div>
  );
};

export default StudentRegister;
