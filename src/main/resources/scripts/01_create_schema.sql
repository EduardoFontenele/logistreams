DROP SCHEMA IF EXISTS logistreams;
CREATE SCHEMA logistreams;
USE logistreams;

DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS sections;
DROP TABLE IF EXISTS inventories;

CREATE TABLE inventories (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE sections (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    inventory_id INT NOT NULL,
    FOREIGN KEY (inventory_id) REFERENCES inventories(id)
);

CREATE TABLE items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    sku VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    stock_quantity INT NOT NULL,
    section_id INT NOT NULL,
    FOREIGN KEY (section_id) REFERENCES sections(id)
);
