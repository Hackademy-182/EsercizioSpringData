CREATE Table authors(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    surname VARCHAR(100),
    email VARCHAR(100)
);

CREATE Table posts(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    body VARCHAR(1000) NOT NULL,
    publish_date CHAR(8),
    author_id BIGINT,
    Foreign Key (author_id) REFERENCES authors(id)
);

CREATE Table comments(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    body VARCHAR(400) NOT NULL,
    date CHAR(8),
    post_id BIGINT,
    Foreign Key (post_id) REFERENCES posts(id)
);