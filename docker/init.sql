CREATE SCHEMA IF NOT EXISTS news_schema;

CREATE TABLE IF NOT EXISTS news_schema.users
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS news_schema.categories
(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS news_schema.news
(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    text TEXT NOT NULL,
    created_at TIMESTAMP,
    category_id BIGINT NOT NULL REFERENCES news_schema.categories(id)
        ON DELETE CASCADE,
    user_id BIGINT NOT NULL REFERENCES news_schema.users(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS news_schema.comments
(
    id BIGSERIAL PRIMARY KEY,
    text VARCHAR(500) NOT NULL,
    created_at TIMESTAMP,
    news_id BIGINT NOT NULL REFERENCES news_schema.news(id)
        ON DELETE CASCADE,
    user_id BIGINT NOT NULL REFERENCES news_schema.users(id)
        ON DELETE CASCADE
);