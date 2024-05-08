CREATE TABLE player (
    idPlayer INT AUTO_INCREMENT PRIMARY KEY,
    legalName VARCHAR(255),
    isAdmin BOOLEAN,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(255),
    score INT
);
