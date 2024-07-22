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