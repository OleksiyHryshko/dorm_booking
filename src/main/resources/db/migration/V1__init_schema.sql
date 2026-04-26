CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       full_name VARCHAR(255) NOT NULL,
                       balance DECIMAL(10, 2) NOT NULL DEFAULT 0.00
);

CREATE TABLE rooms (
                       id BIGSERIAL PRIMARY KEY,
                       room_number VARCHAR(50) NOT NULL UNIQUE,
                       capacity INT NOT NULL,
                       price_per_month DECIMAL(10, 2) NOT NULL
);

CREATE TABLE bookings (
                          id BIGSERIAL PRIMARY KEY,
                          user_id BIGINT NOT NULL REFERENCES users(id),
                          room_id BIGINT NOT NULL REFERENCES rooms(id),
                          check_in_date DATE NOT NULL,
                          check_out_date DATE NOT NULL,
                          status VARCHAR(50) NOT NULL
);