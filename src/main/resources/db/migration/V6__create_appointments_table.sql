-- falta, falta justificada, falta do medico e ok
CREATE TYPE appointments_status AS ENUM (
    'f',
    'fj',
    'fm',
    'ok'
);

CREATE TABLE appointments (
    id SERIAL PRIMARY KEY,
    doctor_id INTEGER REFERENCES doctors(id) NOT NULL,
    patient_id INTEGER REFERENCES patients(id) NOT NULL,
    status appointments_status DEFAULT 'f',
    appointment_date TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);