CREATE EXTENSION IF NOT EXISTS "pgcrypto";
CREATE TABLE users (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(125) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE doctors (
    id SERIAL PRIMARY KEY,
    job VARCHAR(255) NOT NULL,
    crm VARCHAR(9) NOT NULL,
    user_id UUID REFERENCES users(id) ON DELETE CASCADE UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE patients (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone CHAR(14) NOT NULL,
    rg VARCHAR(12),
    insurance VARCHAR(255),
    insuranceNumber VARCHAR(30),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Define ENUM type for week days
CREATE TYPE week_days_enum AS ENUM (
    'Sunday',
    'Monday',
    'Tuesday',
    'Wednesday',
    'Thursday',
    'Friday',
    'Saturday'
);

CREATE TABLE doctors_work_day (
    doctor_id INTEGER NOT NULL,
    day week_days_enum NOT NULL,
    PRIMARY KEY (doctor_id, day),
    FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE
);

CREATE TYPE periods AS ENUM (
    'm',  -- Morning
    't',  -- Afternoon
    'n'   -- Night
);

CREATE TABLE doctors_work_period (
    doctor_id INTEGER REFERENCES doctors(id) ON DELETE CASCADE NOT NULL,
    period periods NOT NULL,
    PRIMARY KEY(doctor_id, period)
);

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

CREATE TABLE patient_referrals (
    id SERIAL PRIMARY KEY,
    patient_id INTEGER REFERENCES patients(id) NOT NULL,
    crm VARCHAR(9) NOT NULL,
    cid VARCHAR(12) NOT NULL,
    job VARCHAR(255) NOT NULL
);