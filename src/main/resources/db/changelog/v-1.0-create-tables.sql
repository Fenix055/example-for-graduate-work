CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone VARCHAR(50),
    role VARCHAR(20),
    image VARCHAR(255)
);

CREATE TABLE ads (
    pk SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    price INT NOT NULL,
    description TEXT,
    image VARCHAR(255),
    author_id INT NOT NULL,
    CONSTRAINT fk_ads_user FOREIGN KEY (author_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE comments (
    pk SERIAL PRIMARY KEY,
    created_at BIGINT NOT NULL,
    text TEXT NOT NULL,
    author_id INT NOT NULL,
    ad_id INT NOT NULL,
    CONSTRAINT fk_comments_user FOREIGN KEY (author_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_comments_ad FOREIGN KEY (ad_id) REFERENCES ads (pk) ON DELETE CASCADE
);