CREATE TABLE public.cuisine (
	id BIGSERIAL NOT NULL,
	"name" varchar(255) NOT NULL,
	CONSTRAINT cuisine_pkey PRIMARY KEY (id)
);