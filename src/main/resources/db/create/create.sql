CREATE TABLE product (
    id UUID PRIMARY KEY,
    name VARCHAR NOT NULL,
    article VARCHAR UNIQUE NOT NULL,
    description VARCHAR NOT NULL,
    price NUMERIC,
    count INTEGER,
    date_time_last_edit TIMESTAMP,
    date_creation DATE,
    category VARCHAR NOT NULL
);