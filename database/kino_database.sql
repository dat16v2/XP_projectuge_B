CREATE USER 'xp'@'localhost' IDENTIFIED BY 'password';
/* CREATE USER 'xp'@'*' IDENTIFIED BY 'password'; */

CREATE DATABASE IF NOT EXISTS xp_projectuge_b;

GRANT ALL PRIVILEGES ON 'xp_projectuge_b' . * TO 'xp'@'localhost';
GRANT ALL PRIVILEGES ON 'xp_projectuge_b' . * TO 'xp'@'*';

FLUSH PRIVILEGES;

use xp_projectuge_b;

CREATE TABLE 'show'(
	id INT NOT NULL AUTO_INCREMENT
);