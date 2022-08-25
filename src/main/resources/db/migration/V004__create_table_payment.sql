CREATE TABLE public.payment (
	id bigint NOT NULL,
	description varchar(255) NOT NULL,
	CONSTRAINT payment_pkey PRIMARY KEY (id)
);