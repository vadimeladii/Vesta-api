CREATE TABLE team (
    id serial PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    project_and_project_role  VARCHAR(100) NOT NULL,
    office   VARCHAR(15) NOT NULL,
	phone VARCHAR(10),
	mobile_phone VARCHAR(20) NOT NULL,
	has_office_keys CHAR(3) NOT NULL,
	work_at_pentalog_since VARCHAR(20) NOT NULL,
	date_of_birth VARCHAR(20) NOT NULL
);