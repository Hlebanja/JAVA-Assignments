CREATE TABLE tbl_user
(
  id       SERIAL PRIMARY KEY,
  ssn      VARCHAR,
  fname    VARCHAR,
  lname    VARCHAR,
  email    VARCHAR,
  isactive BOOLEAN);

