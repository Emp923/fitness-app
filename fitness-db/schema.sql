BEGIN TRANSACTION;

DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

DROP TABLE IF EXISTS user_details CASCADE;

CREATE TABLE user_details (
    user_id INT PRIMARY KEY,
    preferred_name varchar(200),
    availability varchar(200),
    birthday DATE,
    gender varchar(50),
    restrictions varchar(200),
    goals varchar(200),
    comments varchar(200),
    CONSTRAINT FK_user_details FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS trainer_details CASCADE;

CREATE TABLE trainer_details (
    user_id INT PRIMARY KEY,
    name varchar(200),
    bio varchar(255),
    specialty_one varchar(50),
    specialty_two varchar(50),
    certification varchar(50),
    photograph varchar(50),
    CONSTRAINT FK_trainer_details FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS programs CASCADE;

CREATE TABLE programs (
    id SERIAL PRIMARY KEY,
    name varchar(200),
    created_by INT,
    CONSTRAINT FK_programs FOREIGN KEY (created_by) REFERENCES trainer_details(user_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS programs_users CASCADE;

CREATE TABLE programs_users (
    user_id INT,
    program_id INT,
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    CONSTRAINT FK_program_id FOREIGN KEY (program_id) REFERENCES programs(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS program_exercises CASCADE;

CREATE TABLE program_exercises (
    id SERIAL PRIMARY KEY,
    day_of_the_week varchar(200),
    exercise_name varchar(50),
    resistance INT,
    sets INT,
    repetitions INT
);

DROP TABLE IF EXISTS programs_program_exercises CASCADE;

CREATE TABLE programs_program_exercises (
    program_id INT,
    program_exercise_id INT,
    CONSTRAINT FK_program_id FOREIGN KEY (program_id) REFERENCES programs(id) ON DELETE CASCADE,
    CONSTRAINT FK_program_exercise_id FOREIGN KEY (program_exercise_id) REFERENCES  program_exercises(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS exercise_log CASCADE;

CREATE TABLE exercise_log (
    id SERIAL PRIMARY KEY,
    recorded_date DATE,
    resistance INT,
    sets INT,
    repetitions INT,
    exercise_id INT,
    CONSTRAINT FK_exercise_log FOREIGN KEY (exercise_id) REFERENCES program_exercises(id) ON DELETE CASCADE
);

COMMIT TRANSACTION;