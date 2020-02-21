CREATE TABLE Admin
(
  Username VARCHAR(10) PRIMARY KEY
);

CREATE TABLE Role
(
  RoleName VARCHAR,
  Admin    VARCHAR,
  PRIMARY KEY (RoleName, Admin),
  FOREIGN KEY (Admin) references ADMIN (Username) on delete CASCADE
);

CREATE TABLE DATA_CENTER
(
  Name     VARCHAR,
  Capacity INTEGER,
  Admin    VARCHAR,

  PRIMARY KEY (Name),
  FOREIGN KEY (Admin) references ADMIN (Username)
);

CREATE TABLE DATA_CENTER_MANAGED_BY
(
  Admin VARCHAR(10),
  Dname VARCHAR,

  PRIMARY KEY (Admin, Dname),
  FOREIGN KEY (Admin) references ADMIN (Username) ON DELETE CASCADE,
  FOREIGN KEY (Dname) references DATA_CENTER (Name) ON DELETE CASCADE
);

CREATE TABLE SERVER
(
  ServerId      INTEGER, -- why not have this as SERIAL?
  Dname         VARCHAR,
  ComissionTime TIME,
  HardwareModel VARCHAR,

  PRIMARY KEY (ServerId, Dname),
  FOREIGN KEY (Dname) references DATA_CENTER (name) ON DELETE CASCADE
);

CREATE TABLE SERVER_PROCESS
(
  ServerId  INTEGER,
  Dname     VARCHAR,
  ProcessId INTEGER,

  PRIMARY KEY (ServerId, Dname, ProcessId),
  FOREIGN KEY (ServerId, Dname) references SERVER (ServerId, Dname) ON DELETE CASCADE
);

CREATE TABLE DATABASE
(
  ServerId  INTEGER,
  Dname     VARCHAR,
  ProcessId INTEGER,

  DiskSpac  INTEGER,
  Type      VARCHAR CHECK (Type = 'MongoDB' OR type = 'PGSQL'),

  PRIMARY KEY (ServerId, Dname, ProcessId),
  FOREIGN KEY (ServerId, Dname, ProcessId) references SERVER_PROCESS (ServerId, Dname, ProcessId) ON DELETE CASCADE
);

CREATE TABLE CUSTOMER
(
  CustomerId INTEGER PRIMARY KEY
);

CREATE TABLE VM
(
  ServerId   INTEGER,
  Dname      VARCHAR,
  ProcessId  INTEGER,

  CustomerId INTEGER,
  Mem        INTEGER,
  CPU        INTEGER,

  PRIMARY KEY (ServerId, Dname, ProcessId),
  FOREIGN KEY (ServerId, Dname, ProcessId) references SERVER_PROCESS (ServerId, Dname, ProcessId) ON DELETE CASCADE,

  FOREIGN KEY (CustomerId) references CUSTOMER (CustomerId)

);

CREATE TABLE TASK
(
  TaskId     INTEGER,
  CustomerId INTEGER,
  TaskName   VARCHAR,
  Started    TIMESTAMP,
  Finished   TIMESTAMP,

  PRIMARY KEY (TaskId, CustomerId),
  FOREIGN KEY (CustomerId) references CUSTOMER (CustomerId) ON DELETE CASCADE -- Do tasks reference VMs as well?
);


INSERT INTO Admin (Username)
VALUES ('Thiago'),
       ('Hassan'),
       ('Jonas');

INSERT INTO Role (RoleName, Admin)
VALUES ('Programmer', 'Thiago'),
       ('SCRUM Master', 'Hassan'),
       ('Technician', 'Jonas');

INSERT INTO Data_Center (Name, Capacity, Admin)
VALUES ('DataCenter1', 1000, 'Thiago'),
       ('DataCenter2', 500, 'Thiago'),
       ('DataCenter3', 750, 'Hassan');

INSERT INTO Data_center_managed_by (Admin, Dname)
VALUES ('Thiago', 'DataCenter1'),
       ('Thiago', 'DataCenter2'),
       ('Hassan', 'DataCenter3');

INSERT INTO Server (serverid, dname, comissiontime, hardwaremodel)
VALUES (1, 'DataCenter2', '05:20:13', 'XDG-2M'),
       (2, 'DataCenter3', '03:40:21', 'XDG-2M'),
       (3, 'DataCenter3', '07:05:06', 'XDG-2M');

INSERT INTO Server_process (serverid, dname, processid)
VALUES
  --these are not going to be either VM nor Databases
  (2, 'DataCenter3', 1),
  (2, 'DataCenter3', 2),
  (3, 'DataCenter3', 3),
  --these are going into the Database table
  (1, 'DataCenter2', 4),
  (2, 'DataCenter3', 5),
  (3, 'DataCenter3', 6),
  --these are going to be VM
  (1, 'DataCenter2', 7),
  (2, 'DataCenter3', 8),
  (3, 'DataCenter3', 9);

INSERT INTO Database (serverid, dname, processid, diskspac, type)
VALUES (1, 'DataCenter2', 4, 1000, 'PGSQL'),
       (2, 'DataCenter3', 5, 900, 'PGSQL'),
       (3, 'DataCenter3', 6, 950, 'MongoDB');

INSERT INTO customer (customerid)
VALUES (1),
       (2),
       (3);

INSERT INTO VM (serverid, dname, processid, customerid, mem, cpu)
VALUES (1, 'DataCenter2', 7, 1, 300, 7),
       (2, 'DataCenter3', 8, 1, 200, 5),
       (3, 'DataCenter3', 9, 3, 150, 3);

INSERT INTO Task (taskid, customerid, taskname, started, finished)
VALUES (1, 1, 'DB Assignment 1', '2010-05-13 20:13:25', '2011-05-13 20:13:25'),
       (2, 2, 'DB Assignment 2', '2012-05-13 20:13:25', '2012-05-13 20:13:26'),
       (3, 3, 'DB Assignment 3', '2013-05-13 20:13:25', '2013-05-13 20:13:27');

