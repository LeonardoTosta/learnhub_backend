create table users (
	id serial primary key,
	name varchar (40),
	role varchar (15),
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE teachers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(40),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE courses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(40),
    id_user INTEGER,
    id_teacher INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_id_user FOREIGN KEY (id_user) REFERENCES users(id),
    CONSTRAINT fk_id_teacher FOREIGN KEY (id_teacher) REFERENCES teachers(id)
);