CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS todo_items (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    status VARCHAR(100),
    completed BOOLEAN DEFAULT FALSE,
    user_id BIGINT,
    CONSTRAINT fk_todo_user
    FOREIGN KEY (user_id)
    REFERENCES users(id)
    ON DELETE CASCADE
    );
