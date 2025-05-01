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
INSERT INTO trainer_details (user_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (8,'Megan Davis','Works with clients interested in improving overall health and fitness.','Strength Training','Flexibility/Mobility','CPT,CES','photo_megan_davis.jpg');
INSERT INTO trainer_details (user_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (9,'Natalie Paisley','Health and fitness enthusiast incorporating stress management and nutrition.','Yoga','Flexibility/Mobility','CPT,CSCS','photo_nathalie_paisley.jpg');
INSERT INTO trainer_details (user_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (10,'Emily Hughes','One-on-one coaching and class instructor for senior programs.','Yoga','Flexibility/Mobility','CSCS,CFNS','photo_emily_hughes.jpg');
INSERT INTO trainer_details (user_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (11,'Brittany Koch','Rehab specialist for athletic injuries and post surgical patients.','Strength Training','Flexibility/Mobility','CSCS','photo_brittany_koch.jpg');
INSERT INTO trainer_details (user_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (12,'Josh Bauer','Experience working with competitive body builders.','Strength Training','Aesthetics','CPT','photo_josh_bauer.jpg');
INSERT INTO trainer_details (user_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (13,'Antonio Leone','Specializes in youth performance training. Part time high school conditioning coach.','Aesthetics','Performance','CPT,CSCS','photo_antonio_leone.jpg');
INSERT INTO trainer_details (user_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (14,'Jared Taylor','Men`s and Women`s cross country coach. Works with long distance runners.','Performance','Endurance','CSCS,CES','photo_jared_taylor.jpg');
INSERT INTO trainer_details (user_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (15,'Brian Ferguson','Professional sports trainer. Prior experience as trainer for AA baseball team.','Performance','Endurance','CSCS','photo_brian_ferguson.jpg');

-- Placeholder Program Exercises
INSERT INTO program_exercises (id,day_of_the_week,exercise_name,resistance,sets,repetitions) VALUES (16,'Monday','Dumbbell bent-over row',35,3,12);
INSERT INTO program_exercises (id,day_of_the_week,exercise_name,resistance,sets,repetitions) VALUES (17,'Wednesday','Dumbbell Flys',25,3,10);
INSERT INTO program_exercises (id,day_of_the_week,exercise_name,resistance,sets,repetitions) VALUES (18,'Monday, Thursday','Bicep Curls',25,3,20);
INSERT INTO program_exercises (id,day_of_the_week,exercise_name,resistance,sets,repetitions) VALUES (19,'Tuesday, Thursday','Incline Hammer Curls',20,3,15);

COMMIT TRANSACTION;