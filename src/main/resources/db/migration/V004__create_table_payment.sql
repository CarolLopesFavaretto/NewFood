CREATE TABLE public.payment (
	id BIGSERIAL NOT NULL,
	description varchar(255) NOT NULL,
	CONSTRAINT payment_pkey PRIMARY KEY (id)
);