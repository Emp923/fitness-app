BEGIN TRANSACTION;

-- Add Users
INSERT INTO users (username,password_hash,role) VALUES ('user1','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user4','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user5','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user6','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');

-- Add Admin
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

-- Add Trainers
INSERT INTO users (username,password_hash,role) VALUES ('mdavis','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_TRAINER');
INSERT INTO users (username,password_hash,role) VALUES ('npaisley','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_TRAINER');
INSERT INTO users (username,password_hash,role) VALUES ('ehughes','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_TRAINER');
INSERT INTO users (username,password_hash,role) VALUES ('bkoch','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_TRAINER');
INSERT INTO users (username,password_hash,role) VALUES ('jbauer','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_TRAINER');
INSERT INTO users (username,password_hash,role) VALUES ('aleone','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_TRAINER');
INSERT INTO users (username,password_hash,role) VALUES ('jtaylor','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_TRAINER');
INSERT INTO users (username,password_hash,role) VALUES ('bferguson','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_TRAINER');

-- Trainer Details
INSERT INTO trainer_details (user_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (8,'Megan Davis','Bio','Strength Training','Flexibility/Mobility','CPT,CES','photo.png');
INSERT INTO trainer_details (user_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (9,'Natalie Paisley','Bio','Yoga','Flexibility/Mobility','CPT,CSCS','photo.png');
INSERT INTO trainer_details (user_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (10,'Emily Hughes','Bio','Yoga','Flexibility/Mobility','CSCS,CFNS','photo.png');
INSERT INTO trainer_details (user_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (11,'Brittany Koch','Bio','Strength Training','Flexibility/Mobility','CSCS','photo.png');
INSERT INTO trainer_details (user_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (12,'Josh Bauer','Bio','Strength Training','Aesthetics','CPT','photo.png');
INSERT INTO trainer_details (user_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (13,'Antonio Leone','Bio','Aesthetics','Performance','CPT,CSCS','photo.png');
INSERT INTO trainer_details (user_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (14,'Jared Taylor','Bio','Performance','Endurance','CSCS,CES','photo.png');
INSERT INTO trainer_details (user_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (15,'Brian Ferguson','Bio','Performance','Endurance','CSCS','photo.png');

COMMIT TRANSACTION;