CREATE SCHEMA IF NOT EXISTS booking;
use booking;

CREATE TABLE CALENDAR_BOOK(
	ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	END TIMESTAMP,
	START TIMESTAMP,
	RESOURCE_ID BIGINT,
	USER_REF_ID BIGINT
);

CREATE TABLE RESOURCE(
	ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	DESCRIPTION VARCHAR(255) NOT NULL,
	TYPE VARCHAR(255) NOT NULL
);

CREATE TABLE USER(
	ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	EMAIL VARCHAR(255) NOT NULL,
	NAME VARCHAR(255) NOT NULL,
	SURNAME VARCHAR(255) NOT NULL,
	CHECK_SUM VARCHAR(255) NOT NULL
);

ALTER TABLE CALENDAR_BOOK ADD CONSTRAINT FK_RESOURCE_ID FOREIGN KEY(RESOURCE_ID) REFERENCES RESOURCE(ID);
ALTER TABLE CALENDAR_BOOK ADD CONSTRAINT FK_USER_ID FOREIGN KEY(USER_REF_ID) REFERENCES USER(ID);