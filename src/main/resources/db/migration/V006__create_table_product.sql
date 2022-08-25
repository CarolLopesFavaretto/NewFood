CREATE TABLE public.product (
	id bigint NOT NULL,
	active bool NULL,
	description varchar(255) NULL,
	"name" varchar(255) NULL,
	price numeric(19, 2) NULL,
	restaurant_id int8 NULL,
	CONSTRAINT product_pkey PRIMARY KEY (id)
);