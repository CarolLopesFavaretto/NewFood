CREATE TABLE public.state (
	id BIGSERIAL NOT NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT state_pkey PRIMARY KEY (id)
);