create table if not exists product (
    id bigint primary key,
    name varchar,
    category varchar,
    price int,
    person_id varchar,

    constraint fk_person  FOREIGN KEY(person_id) references person(email)
)