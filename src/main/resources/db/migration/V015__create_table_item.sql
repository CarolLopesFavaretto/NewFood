CREATE TABLE public.item (
	id BIGSERIAL NOT NULL,
	observation varchar(255) NULL,
	quantity int4 NULL,
	total numeric(19, 2) NULL,
	value numeric(19, 2) NULL,
	orders_id int8 NOT NULL,
	product_id int8 NOT NULL,
	CONSTRAINT item_pkey PRIMARY KEY (id)
);

-- public.item foreign keys

ALTER TABLE public.item ADD CONSTRAINT fk67l3m62tc4idi3yj0362hoqao FOREIGN KEY (orders_id) REFERENCES public.orders(id);
ALTER TABLE public.item ADD CONSTRAINT fkd1g72rrhgq1sf7m4uwfvuhlhe FOREIGN KEY (product_id) REFERENCES public.product(id);