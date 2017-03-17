use xp_projectuge_b;

# CREATE SAMPLE GENRES.
INSERT INTO `genre` (name) VALUES ('Drama');
INSERT INTO `genre` (name) VALUES ('Comedy');
INSERT INTO `genre` (name) VALUES ('Action');
INSERT INTO `genre` (name) VALUES ('Romance');
INSERT INTO `genre` (name) VALUES ('Horror');
INSERT INTO `genre` (name) VALUES ('War film');
INSERT INTO `genre` (name) VALUES ('Science Fiction');

# CREATE SAMPLE ACTORS.
INSERT INTO `actor` (first_name, last_name) VALUES ('Leonardo', 'DiCaprio');
INSERT INTO `actor` (first_name, last_name) VALUES ('Johnny', 'Depp');
INSERT INTO `actor` (first_name, last_name) VALUES ('Tom', 'Hanks');
INSERT INTO `actor` (first_name, last_name) VALUES ('Anne', 'Hathaway');
INSERT INTO `actor` (first_name, last_name) VALUES ('Scarlett', 'Johansson');
INSERT INTO `actor` (first_name, last_name) VALUES ('Emma', 'Stone');

# CREATE SHOW
INSERT INTO `show` (title, runtime, poster_path) VALUES ('Saving Private Ryan', 180, 'imgs/spr180px.jpg');