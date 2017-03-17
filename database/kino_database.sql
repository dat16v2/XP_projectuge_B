CREATE USER 'xp'@'localhost' IDENTIFIED BY 'password';
/* CREATE USER 'xp'@'*' IDENTIFIED BY 'password'; */

CREATE DATABASE IF NOT EXISTS xp_projectuge_b;

GRANT ALL PRIVILEGES ON xp_projectuge_b . * TO 'xp'@'localhost';
/* GRANT ALL PRIVILEGES ON xp_projectuge_b . * TO 'xp'@'*'; */

FLUSH PRIVILEGES;

use xp_projectuge_b;

CREATE TABLE `show`(
	id INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(250) NOT NULL,
	runtime INT,
	poster_path TEXT,
	PRIMARY KEY (id)
);

CREATE TABLE `genre`(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(250) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE `actor`(
	id INT NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(250) NOT NULL,
	last_name VARCHAR(250) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE `show_actor`(
	id INT NOT NULL AUTO_INCREMENT,
	id_actor INT NOT NULL,
	id_show INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE `show_genre`(
	id INT NOT NULL AUTO_INCREMENT,
	id_genre INT,
	id_show INT,
	PRIMARY KEY (id)
);