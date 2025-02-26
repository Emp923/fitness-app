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

DROP TABLE IF EXISTS program_exercises CASCADE;

CREATE TABLE program_exercises (
    id SERIAL PRIMARY KEY,
    day_of_the_week varchar(200),
    exercise varchar(50),
    resistance INT,
    sets INT,
    repetitions INT,
    user_id INT,
    CONSTRAINT FK_program_exercises FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
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