# Task7
Employee Database App
```
# Employee Database App – Java JDBC

## 📌 Overview
This is a simple **Java JDBC** application that connects to a MySQL database and performs **CRUD** operations (Create, Read, Update, Delete) on an Employee table.

It demonstrates:
- JDBC database connectivity
- Prepared statements for security
- SQL query execution
- Menu-driven console interface

---

## 🛠 Technologies Used
- **Java** (JDK 8+)
- **MySQL** (or PostgreSQL with minor changes)
- **JDBC Driver** for MySQL
- **VS Code** / IntelliJ / Eclipse

---

## 📂 Features
- Add new employee records
- View all employee records
- Update employee details
- Delete employee records
- Prevent SQL Injection using `PreparedStatement`

---

## ⚙️ Setup Instructions

### 1️⃣ Install & Configure MySQL
1. Install MySQL Server.
2. Open MySQL Command Line / Workbench.
3. Create the database and table:
   ```sql
   CREATE DATABASE employee_db;
   USE employee_db;

   CREATE TABLE employees (
       id INT PRIMARY KEY AUTO_INCREMENT,
       name VARCHAR(100) NOT NULL,
       department VARCHAR(100),
       salary DECIMAL(10,2)
   );
