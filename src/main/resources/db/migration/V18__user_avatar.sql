CREATE TABLE avatar
(
    id         serial PRIMARY KEY,
    user_image bytea,
    image_name VARCHAR(100),
    users_id   INT NOT NULL,

    CONSTRAINT avatar_users_id_fkey FOREIGN KEY (users_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);