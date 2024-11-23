create table project (
    id bigint auto_increment primary key,
    name_project varchar(255) not null,
    description varchar(2000),
    created_date date
);

create table users (
     id bigint auto_increment primary key,
     username varchar(32) not null,
     password varchar(64) not null,
     email varchar(64),
     role varchar(16) not null
);


create table users_projects (
     id bigint auto_increment primary key,
     relation_entity_id bigint,
     project_id bigint not null,
     user_id bigint not null,
     constraint fk_project foreign key (project_id) references project(id) on delete cascade,
     constraint fk_user foreign key (user_id) references users(id) on delete cascade
);
