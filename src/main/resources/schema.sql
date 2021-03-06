DROP TABLE IF EXISTS product;

CREATE TABLE product (
  id INT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  name_extra VARCHAR(250) NOT NULL,
  price VARCHAR(250) NOT NULL,
  unit_price VARCHAR(250) NOT NULL,
  available BOOLEAN DEFAULT TRUE
);