CREATE TABLE subject
(
    id                  SERIAL PRIMARY KEY,
    position_x          FLOAT   NOT NULL,
    position_y          FLOAT   NOT NULL,
    scale               FLOAT   NOT NULL,
    editable            BOOLEAN NOT NULL,
    rotation            FLOAT   NOT NULL,
    floor_id            INT     NOT NULL,
    subject_template_id INT     NOT NULL,

    CONSTRAINT subject_subject_template_id_fkey FOREIGN KEY (subject_template_id)
        REFERENCES subject_templates (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);