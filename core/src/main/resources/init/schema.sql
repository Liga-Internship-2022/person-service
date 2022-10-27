create table if not exists medical_card
(
    id            bigserial not null unique primary key,
    client_status char      not null,
    med_status    char,
    registry_dt   date      not null,
    comment       text
);

create table if not exists contact
(
    id           bigserial    not null unique primary key,
    phone_number varchar(32)  not null,
    email        varchar(128) not null,
    profile_link text         not null
);

create table if not exists illness
(
    id              bigserial not null unique primary key,
    medical_card_id bigint references medical_card (id) on delete cascade,
    type_id         bigint    not null,
    heaviness       char      not null,
    appearance_dttm timestamp not null,
    recovery_dt     date      not null
);

create table if not exists person_data
(
    id              bigserial    not null unique primary key,
    last_name       varchar(255) not null,
    first_name      varchar(255) not null,
    birth_dt        date         not null,
    age             smallint     not null,
    sex             char         not null,
    contact_id      bigint       not null references contact (id) on delete cascade,
    medical_card_id bigint       not null references medical_card (id) on delete cascade,
    parent_id       bigint check ( parent_id <> person_data.id ) references person_data (id)
);

create table if not exists address
(
    id         bigserial    not null unique primary key,
    contact_id bigint       not null references contact (id) on delete cascade,
    country_id bigint       not null,
    city       varchar(255) not null,
    index      integer      not null,
    street     varchar(255) not null,
    building   varchar(32)  not null,
    flat       varchar(32)  not null
);
