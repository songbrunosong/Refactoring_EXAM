create table member
(
    id bigint generated by default as identity,
    name varchar(255),
    primary key (id)
);

create table team
(
    id bigint generated by default as identity,
    team_name varchar(255),
    primary key (id)
);