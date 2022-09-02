CREATE TABLE public.restaurant (
	id BIGSERIAL NOT NULL,
	"name" varchar(255) NULL,
	shipping numeric(19, 2) NULL,
	cuisine_id int8 NULL,
	cuisine_type int8 NULL,
	payment_id int8 NULL,
	complement varchar(255) NULL,
	district varchar(255) NULL,
	"number" varchar(255) NULL,
	public_place varchar(255) NULL,
	zip_code varchar(255) NULL,
	city_id int8 NULL,
	created_at timestamp NULL,
	updated_at timestamp NULL,
	CONSTRAINT restaurant_pkey PRIMARY KEY (id)
);


-- public.restaurant foreign keys

ALTER TABLE public.restaurant ADD CONSTRAINT fkpo7kdq032sw4m3dxs8fgeylr3 FOREIGN KEY (payment_id) REFERENCES public.payment(id);