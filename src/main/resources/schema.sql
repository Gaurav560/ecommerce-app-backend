CREATE TABLE IF NOT EXISTS product (
                                       id SERIAL PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    brand VARCHAR(255),
    price NUMERIC(19,2) NOT NULL,
    category VARCHAR(255),
    stock_quantity INTEGER,
    release_date DATE,
    is_active BOOLEAN

    );