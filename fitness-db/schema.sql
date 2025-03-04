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

COMMIT TRANSACTION;