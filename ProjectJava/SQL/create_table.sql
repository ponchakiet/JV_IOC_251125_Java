create schema quanlykhoahoc;

set search_path to quanlykhoahoc;

CREATE TABLE student
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    dob        DATE         NOT NULL,
    email      VARCHAR(100) NOT NULL UNIQUE,
    sex        BOOLEAN      NOT NULL,
    phone      VARCHAR(20),
    password   VARCHAR(255) NOT NULL,
    created_at DATE DEFAULT CURRENT_DATE
);

CREATE TABLE admin
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE course
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    duration   INT          NOT NULL,
    instructor VARCHAR(100) NOT NULL,
    created_at DATE DEFAULT CURRENT_DATE
);

CREATE TYPE enrollment_status AS ENUM ('WAITING', 'DENIED', 'CANCEL', 'CONFIRM', 'DELETED');

CREATE TABLE enrollment
(
    id            SERIAL PRIMARY KEY,
    student_id    INT NOT NULL,
    course_id     INT NOT NULL,
    registered_at TIMESTAMP         DEFAULT CURRENT_TIMESTAMP,
    status        enrollment_status DEFAULT 'WAITING',
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES student (id) ON DELETE CASCADE,
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE
);
