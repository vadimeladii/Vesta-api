drop table vesta_schema.user;
drop table vesta_schema.user_role;
drop table vesta_schema.role;

create table vesta_schema.user(
    id serial primary key,
    name varchar (50) UNIQUE not null ,
    lastname varchar (50) unique  not null ,
    password varchar (50) not null,
    email varchar (100) unique not null,
    created_on timestamp not null,
    last_login timestamp
    );

create table vesta_schema.role(
    id serial primary key,
    name varchar (100) unique not null
);

create table user_role(
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT user_role_role_id_fkey FOREIGN KEY (role_id)
        REFERENCES role (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT user_role_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES vesta_schema.user (id) MATCH SIMPLE
        ON UPDATE NO ACTION ON DELETE NO ACTION
);


