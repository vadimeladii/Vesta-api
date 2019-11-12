ALTER TABLE avatar DROP CONSTRAINT  avatar_users_id_fkey;

ALTER TABLE avatar ADD CONSTRAINT  avatar_users_id_fkey FOREIGN KEY (users_id)
REFERENCES users (id) MATCH SIMPLE
            ON UPDATE NO ACTION ON DELETE CASCADE