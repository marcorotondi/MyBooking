ALTER TABLE CALENDAR_BOOK ADD COLUMN NATURAL_ID VARCHAR(255);

UPDATE CALENDAR_BOOK SET NATURAL_ID = '0';

ALTER TABLE CALENDAR_BOOK ALTER COLUMN NATURAL_ID SET NOT NULL;