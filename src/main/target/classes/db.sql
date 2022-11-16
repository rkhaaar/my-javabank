DROP DATABASE IF EXISTS javabank;
CREATE DATABASE javabank;
USE javabank;

DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS account;

CREATE TABLE customer (
  id            INT AUTO_INCREMENT PRIMARY KEY,
  email         VARCHAR(255),
  first_name    VARCHAR(255),
  last_name     VARCHAR(255),
  phone         VARCHAR(255),
  creation_time DATETIME,
  update_time   DATETIME
);

CREATE TABLE account(
  account_type  VARCHAR(31) NOT NULL,
  id            INT AUTO_INCREMENT PRIMARY KEY,
  balance       DOUBLE NOT NULL,
  customer_id   INT NOT NULL ,
  creation_time DATETIME,
  update_time   DATETIME,
  FOREIGN KEY (customer_id) REFERENCES customer (id)
);

DELETE FROM customer;
INSERT INTO customers_table(id, creationTime, email, firstName, lastName, phone) VALUES
(1, TIMESTAMP '2017-10-10 08:45:56.468','sg@mail.pt', 'Sérgio', 'Gouveia', '919909812'),
(2, TIMESTAMP '2017-10-10 08:45:56.481','talefe@academia.com', 'Sara', 'Talefe', '929671231'),
(3, TIMESTAMP '2017-10-10 08:45:56.482','danlo@tinder.com', 'Rúben', 'Maya', '91923145'),
(4, TIMESTAMP '2017-10-10 08:45:56.482','saralopes@noob.b', 'Sara', 'Lopes', '96789098'),
(5, TIMESTAMP '2017-10-10 08:45:56.482', 'rolinho@soulindo.true', 'Diogo', 'Rolo', '911222333');


DELETE FROM account;


