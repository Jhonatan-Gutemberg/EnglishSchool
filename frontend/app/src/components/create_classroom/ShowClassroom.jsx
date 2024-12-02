import React, { useEffect, useState } from "react";
import { FaUsers, FaChalkboardTeacher } from "react-icons/fa";

const ShowClassroom = ({ teacherId: propTeacherId }) => {
    const [classes, setClasses] = useState([]);
    const [studentCounts, setStudentCounts] = useState({});
    const [teacherId, setTeacherId] = useState(null);

    useEffect(() => {
        const storedTeacherId = localStorage.getItem("auth-id");
        if (storedTeacherId) {
            setTeacherId(storedTeacherId);
        } else if (propTeacherId) {
            setTeacherId(propTeacherId);
        } else {
            alert("ID do professor não encontrado.");
        }
    }, [propTeacherId]);

    const fetchClasses = async () => {
        if (!teacherId) return;

        const token = localStorage.getItem('auth-token'); 

        try {
            const response = await fetch(`http://localhost:8080/classroom/teacher/${teacherId}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}` 
                }
            });

            if (response.ok) {
                const result = await response.json();
                setClasses(result);
                result.forEach((classroom) => fetchStudentCount(classroom.id, token)); 
            } else {
                alert("Erro ao buscar turmas do professor.");
            }
        } catch (error) {
            console.error("Erro na requisição:", error);
            alert("Erro ao buscar turmas. Verifique a conexão com o servidor.");
        }
    };

    const fetchStudentCount = async (classroomId, token) => {
        try {
            const response = await fetch(`http://localhost:8080/classroom/${classroomId}/student-count`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                }
            });
            if (response.ok) {
                const studentCount = await response.json();
                setStudentCounts((prev) => ({
                    ...prev,
                    [classroomId]: studentCount,
                }));
            } else {
                console.error(`Erro ao buscar quantidade de alunos para a turma ${classroomId}`);
            }
        } catch (error) {
            console.error("Erro na requisição:", error);
        }
    };

    useEffect(() => {
        if (teacherId) {
            fetchClasses();
        }
    }, [teacherId]);

    return (
        <div className="card">
            <h2>Minhas Turmas</h2>
            <div className="classroom-grid">
                {classes.length > 0 ? (
                    classes.map((classroom) => (
                        <div key={classroom.id} className="classroom-card">
                            <div className="classroom-header">
                                <h3>{classroom.name}</h3>
                                <FaChalkboardTeacher size={30} color="rgb(66 92 179)" />
                            </div>
                            <div className="classroom-details">
                                <p>
                                    <FaUsers /> <strong>Alunos:</strong>{" "}
                                    {studentCounts[classroom.id] !== undefined
                                        ? studentCounts[classroom.id]
                                        : "Carregando..."}
                                </p>
                            </div>
                        </div>
                    ))
                ) : (
                    <p>Nenhuma turma encontrada.</p>
                )}
            </div>
        </div>
    );
};


export default ShowClassroom;
