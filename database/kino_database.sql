CREATE USER 'xp'@'localhost' IDENTIFIED BY 'password';
/* CREATE USER 'xp'@'%' IDENTIFIED BY 'password'; */

CREATE DATABASE IF NOT EXISTS xp_projectuge_b;

GRANT ALL PRIVILEGES ON xp_projectuge_b . * TO 'xp'@'localhost';
/* GRANT ALL PRIVILEGES ON xp_projectuge_b . * TO 'xp'@'%'; */

FLUSH PRIVILEGES;

use xp_projectuge_b;

CREATE TABLE `show`(
	id INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(250) NOT NULL,
	runtime INT,
	poster_path TEXT,
	last_updated TIMESTAMP,
	id_auditorium INT,
	`date` DATE,
	`time` TIME,
	PRIMARY KEY (id)
);

CREATE TABLE `genre`(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(250) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE `rating`(
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

CREATE TABLE `show_rating`(
	id INT NOT NULL AUTO_INCREMENT,
	id_rating INT,
	id_show INT,
	PRIMARY KEY (id)
);

CREATE TABLE `booking`(
	id INT NOT NULL AUTO_INCREMENT,
	name VARCHAR(250) NOT NULL,
	phone_number VARCHAR(250) NOT NULL,
	id_show INT,
	ticket_amount INT,
	PRIMARY KEY (id),

	CONSTRAINT fk_show
	FOREIGN KEY (id_show)
	REFERENCES `show`(id)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
);

CREATE TABLE `auditorium`(
	id INT NOT NULL AUTO_INCREMENT,
	total_amount_seats INT,
	name VARCHAR(250),
	PRIMARY KEY (id)
);

CREATE TABLE `showing`(
	id INT NOT NULL AUTO_INCREMENT,
	id_show INT NOT NULL,
	`date` DATE,
	`time` TIME,
	id_auditorium INT,

	PRIMARY KEY (id)
);