INSERT INTO INFOENTITY (ENTITY_TYPE,EMAIL) VALUES ('P','cph-mf226@cphbusiness.dk');
SET @infoentity = LAST_INSERT_ID();
INSERT INTO PERSON (ID,FIRSTNAME,LASTNAME) VALUES (LAST_INSERT_ID(),'Mads','Floistrup');
