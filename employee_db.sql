
-- SQL script to create the employee_db database and employees table

-- Create database
CREATE DATABASE IF NOT EXISTS employee_db;

-- Use the database
USE employee_db;

-- Create employees table
CREATE TABLE IF NOT EXISTS employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100),
    salary DECIMAL(10,2)
);

-- Optional: Insert some sample data
INSERT INTO employees (name, department, salary) VALUES
('John Doe', 'IT', 55000.00),
('Jane Smith', 'HR', 48000.00),
('Michael Brown', 'Finance', 62000.00);
