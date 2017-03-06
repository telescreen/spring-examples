CREATE TABLE IF NOT EXISTS users (
    id serial primary key,
    username varchar(45) not null unique,
    password varchar(60) not null,
    enabled boolean not null default true
);

CREATE TABLE IF NOT EXISTS roles (
    id serial primary key,
    role varchar(45) not null,
    userid serial references users(id)
);

commit;