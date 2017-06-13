DROP DATABASE IF EXISTS CodedChat;
CREATE DATABASE CodedChat;
USE CodedChat;


CREATE TABLE Users (
	Id INT NOT NULL AUTO_INCREMENT,
	Name VARCHAR(45) NOT NULL,
	
    PRIMARY KEY(Id));


CREATE TABLE MessageTypes (
	Id INT NOT NULL AUTO_INCREMENT,
	Name VARCHAR(45) NOT NULL,
    CreatorUserId INT,
    
    FOREIGN KEY (CreatorUserId) REFERENCES Users (Id),
	
    PRIMARY KEY(Id));


CREATE TABLE Messages (
	Id INT NOT NULL AUTO_INCREMENT,
	Text VARCHAR(255) NOT NULL,
    
    UploadedDate DATE,
    
    ToUserId INT NOT NULL,
    TypeId INT NOT NULL,
    
	FOREIGN KEY (ToUserId) REFERENCES Users (Id),
	FOREIGN KEY (TypeId) REFERENCES MessageTypes (Id),
    
    PRIMARY KEY(Id));


CREATE TABLE Solutions (
	Id INT NOT NULL AUTO_INCREMENT,
	DecodedText VARCHAR(255) NOT NULL,
    
	UserId INT NOT NULL,
    MessageId INT NOT NULL,
    
    FOREIGN KEY (UserId) REFERENCES Users (Id),
	FOREIGN KEY (MessageId) REFERENCES Messages (Id),

	PRIMARY KEY (Id));




