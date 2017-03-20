
CREATE TABLE User(
  username VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  PRIMARY KEY (username)
);

CREATE TABLE Profile(
  username VARCHAR(100) NOT NULL,
  NAME VARCHAR(100) NOT NULL,
  VALUE VARCHAR(100) NOT NULL,
  FOREIGN KEY (username) REFERENCES User(username)
);

CREATE TABLE Coordinates(
  ID  VARCHAR(36) NOT NULL,
  Lat FLOAT( 10, 6 ) NOT NULL ,
  Lon FLOAT( 10, 6 ) NOT NULL ,
  Radius INT NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE Location(
  NAME VARCHAR(100) NOT NULL,
  SSID VARCHAR(100) NULL,
  BLE VARCHAR(100) NULL,
  COORDID VARCHAR(36) NOT NULL,
  PRIMARY KEY (NAME),
  FOREIGN KEY (COORDID) REFERENCES Coordinates(ID)
);

CREATE TABLE Message(
  ID VARCHAR(36) NOT NULL,
  LOCATION VARCHAR(100) NOT NULL,
  CONTENT VARCHAR(100) NOT NULL,
  CREATIONTIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  STARTTIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  ENDTIME TIMESTAMP NULL,
  PUBLISHER VARCHAR(100) NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (LOCATION) REFERENCES Location(name),
  FOREIGN KEY (PUBLISHER) REFERENCES User(username)
);


CREATE TABLE List(
  MSGID VARCHAR(36) NOT NULL,
  TYPE VARCHAR(100) NOT NULL,
  NAME VARCHAR(100) NULL,
  VALUE VARCHAR(100) NULL,
  FOREIGN KEY (TYPE) REFERENCES ListType(NAME),
  FOREIGN KEY (NAME) REFERENCES Profile(NAME),
  FOREIGN KEY (VALUE) REFERENCES Profile(VALUE),
  FOREIGN KEY (MSGID) REFERENCES Message(ID),
  PRIMARY KEY (MSGID,TYPE)
);

CREATE TABLE ListType(
  NAME VARCHAR(100) NOT NULL,
  PRIMARY KEY (NAME)
);