CREATE TABLE webpage
(
  ID         SERIAL PRIMARY KEY,
  Author     INTEGER,
  URL        VARCHAR,
  Content    VARCHAR,
  Popularity INTEGER,

  FOREIGN KEY (Author) references tbl_user(ID)
);

