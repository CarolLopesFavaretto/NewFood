CREATE TABLE public.users (
	id BIGSERIAL NOT NULL,
	created_at timestamp NULL,
	email varchar(255) NULL,
	"name" varchar(255) NULL,
	"password" varchar(255) NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id)
);