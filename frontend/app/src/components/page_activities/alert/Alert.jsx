import React from 'react';
import './Alert.css'; 

const Alert = ({ type, message, onClose }) => {
  const alertTypes = {
    success: 'alert-success',
    error: 'alert-error',
    info: 'alert-info',
  };

  return (
    <div className={`alert ${alertTypes[type] || 'alert-info'}`}>
      <div className="alert-content">
        <span className="alert-message">{message}</span>
        <button className="alert-close-btn" onClick={onClose}>×</button>
      </div>
    </div>
  );
};

export default Alert;
