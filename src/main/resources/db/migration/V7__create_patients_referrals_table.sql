CREATE TABLE patient_referrals (
    id SERIAL PRIMARY KEY,
    patient_id INTEGER REFERENCES patients(id) NOT NULL,
    crm VARCHAR(9) NOT NULL,
    cid VARCHAR(12) NOT NULL,
    job VARCHAR(255) NOT NULL
);