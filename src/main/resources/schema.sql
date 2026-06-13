CREATE TABLE customers (
     id SERIAL PRIMARY KEY,
     full_name VARCHAR(255) NOT NULL,
     email VARCHAR(255) NOT NULL UNIQUE,
     social_security_number VARCHAR(20) NOT NULL UNIQUE
)