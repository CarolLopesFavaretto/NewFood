CREATE TABLE public."permission" (
	id bigint NOT NULL,
	description varchar(255) NOT NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT permission_pkey PRIMARY KEY (id)
);