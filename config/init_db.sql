-- auto-generated definition
create table resume
(
    uuid      char(36) not null
        constraint resume_pk
            primary key,
    full_name text not null
);

alter table resume
    owner to resume_admin;



-- auto-generated definition
create table contact
(
    id          serial   not null
        constraint contact_pk
            primary key,
    resume_uuid char(36) not null
        constraint contact_resume_uuid_fk
            references resume
            on delete cascade,
    type        text     not null,
    value       text     not null
);

alter table contact
    owner to resume_admin;

create unique index contact_uuid_type_index
    on contact (resume_uuid, type);

create table section (
    id          SERIAL PRIMARY KEY,
    resume_uuid CHAR(36) NOT NULL REFERENCES resume(uuid) ON DELETE CASCADE,
    type        TEXT NOT NULL,
    content     TEXT NOT NULL
);

CREATE UNIQUE INDEX section_idx ON contact (resume_uuid, type);
