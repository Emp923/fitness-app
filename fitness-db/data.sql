BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user1','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user4','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user5','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user6','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO trainer_details (trainer_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (1,'Megan Davis','Bio','Strength Training','Flexibility/Mobility','CPT,CES','photo.png');
INSERT INTO trainer_details (trainer_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (2,'Natalie Paisley','Bio','Yoga','Flexibility/Mobility','CPT,CSCS','photo.png');
INSERT INTO trainer_details (trainer_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (3,'Emily Hughes','Bio','Yoga','Flexibility/Mobility','CSCS,CFNS','photo.png');
INSERT INTO trainer_details (trainer_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (4,'Brittany Koch','Bio','Strength Training','Flexibility/Mobility','CSCS','photo.png');
INSERT INTO trainer_details (trainer_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (5,'Josh Bauer','Bio','Strength Training','Aesthetics','CPT','photo.png');
INSERT INTO trainer_details (trainer_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (6,'Antonio Leone','Bio','Aesthetics','Performance','CPT,CSCS','photo.png');
INSERT INTO trainer_details (trainer_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (7,'Jared Taylor','Bio','Performance','Endurance','CSCS,CES','photo.png');
INSERT INTO trainer_details (trainer_id,name,bio,specialty_one,specialty_two,certification,photograph) VALUES (8,'Brian Ferguson','Bio','Performance','Endurance','CSCS','photo.png');

COMMIT TRANSACTION;