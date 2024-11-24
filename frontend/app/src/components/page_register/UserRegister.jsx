import React, { useState } from 'react';
import './TeacherRegister.css';

const UserRegister = () => {
    const [formData, setFormData] = useState({
        name: '',
        databirth: '',
        email: '',
        type: 'USER',
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

    React.useEffect(() => {
        fetch('http://localhost:8080/classroom/all')
            .then(response => response.json())
            .then(data => setClassrooms(data))
            .catch(err => console.error('Erro ao carregar salas de aula:', err));
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        setIsSubmitting(true);
        setError('');

        try {
            const response = await fetch('http://localhost:8080/student/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData),
            });

            if (!response.ok) {
                throw new Error('Erro ao cadastrar');
            }

            alert('Cadastro realizado com sucesso!');
        } catch (err) {
            setError(err.message);
        } finally {
            setIsSubmitting(false);
        }
    };

    return (
        <div className="user-register">
            <h2>Cadastro de Usuário</h2>
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
                    <label htmlFor="type">Tipo de Usuário</label>
                    <select
                        id="type"
                        name="type"
                        value={formData.type}
                        onChange={handleChange}
                        required
                    >
                        <option value="USER">Aluno</option>
                        <option value="TEACHER">Professor</option>
                    </select>
                </div>

                {formData.type === 'USER' && (
                    <>
                        <div className="form-group">
                            <label htmlFor="id_classroom">Sala de Aula</label>
                            <select
                                id="id_classroom"
                                name="id_classroom"
                                value={formData.id_classroom}
                                onChange={handleChange}
                                required
                            >
                                <option value="">Selecione a sala</option>
                                {classrooms.map((classroom) => (
                                    <option key={classroom.id} value={classroom.id}>
                                        {classroom.name}
                                    </option>
                                ))}
                            </select>
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
                    </>
                )}

                {formData.type === 'TEACHER' && (
                    <>
                        <div className="form-group">
                            <label htmlFor="department">Departamento</label>
                            <input
                                type="text"
                                id="department"
                                name="department"
                                value={formData.department}
                                onChange={handleChange}
                                required
                            />
                        </div>
                    </>
                )}

                <div className="form-group">
                    <button type="submit" disabled={isSubmitting}>
                        {isSubmitting ? 'Cadastrando...' : 'Cadastrar'}
                    </button>
                </div>
            </form>
        </div>
    );
};

export default UserRegister;
