import React, { useState } from "react";

const AssociateClassroom = () => {
  const [classId, setClassId] = useState("");
  const [teacherId, setTeacherId] = useState("");

  const handleAssociateClass = async () => {
    if (!classId.trim() || !teacherId.trim()) {
      alert("Por favor, insira os IDs da turma e do professor.");
      return;
    }

    const response = await fetch(`http://localhost:8080/classroom/update/${classId}/${teacherId}`, {
      method: "PUT",
    });

    if (response.ok) {
      alert("Turma associada com sucesso!");
      setClassId("");
      setTeacherId("");
    } else {
      alert("Erro ao associar turma.");
    }
  };

  return (
    <div className="card">
      <h2>Associar Turma</h2>
      <input
        type="number"
        placeholder="ID da Turma"
        value={classId}
        onChange={(e) => setClassId(e.target.value)}
      />
      <input
        type="number"
        placeholder="ID do Professor"
        value={teacherId}
        onChange={(e) => setTeacherId(e.target.value)}
      />
      <button onClick={handleAssociateClass}>Associar</button>
    </div>
  );
};

export default AssociateClassroom;
