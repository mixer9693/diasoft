create table if not exists public.contact_type
(
	id integer not null
		constraint contact_type_pkey
			primary key,
	type varchar(255)
);

alter table public.contact_type owner to test;

create table if not exists public.person
(
	id integer not null
		constraint person_pkey
			primary key,
	first_name varchar(80),
	last_name varchar(80),
	middle_name varchar(80),
	position varchar(255)
);

alter table public.person owner to test;

create table if not exists public.contacts
(
	id integer not null
		constraint contacts_pkey
			primary key,
	number varchar(20),
	contact_type_id integer not null
		constraint fkoh699kghkv6lyjokjrwqnyckx
			references public.contact_type,
	person_id integer not null
		constraint fkcegkttvev04hc3fsax8wsal4o
			references public.person
);

alter table public.contacts owner to test;

create sequence public.hibernate_sequence;

alter sequence public.hibernate_sequence owner to test;





insert into public.contact_type (id, type) 
    values 
        (1, 'рабочий'),
        (2, 'личный');

insert into public.person (id, first_name, last_name, middle_name, position)
    values
        (3, 'Эдуард', 'Евгеньевич', 'Балыга', 'прораб'),
        (4, 'Роман', 'Игоревич', 'Стрижевский', 'администратор');

insert into public.contacts (id, number, contact_type_id, person_id) 
    values 
        (5, '79814785695', 1, 3),
        (6, '78541236547', 1, 4),
        (7, '75896321457', 2, 4);

alter sequence public.hibernate_sequence restart with 10;
