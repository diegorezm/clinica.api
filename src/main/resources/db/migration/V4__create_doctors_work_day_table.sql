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