CREATE TABLE floor (
    id              serial PRIMARY KEY,
    floor_name      VARCHAR(50) NOT NULL,
    company_id      INT NOT NULL,
    CONSTRAINT floor_companies_id_fkey FOREIGN KEY (company_id)
        REFERENCES company(id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);