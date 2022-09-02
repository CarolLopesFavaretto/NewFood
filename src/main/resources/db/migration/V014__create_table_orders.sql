CREATE TABLE public.orders (
	id BIGSERIAL NOT NULL,
	complement varchar(255) NULL,
	district varchar(255) NULL,
	"number" varchar(255) NULL,
	public_place varchar(255) NULL,
	zip_code varchar(255) NULL,
	amount numeric(19, 2) NULL,
	created_at timestamp NULL,
	date_cancellation timestamp NULL,
	date_confirmation timestamp NULL,
	date_delivery timestamp NULL,
	fee_shipping numeric(19, 2) NULL,
	status int4 NULL,
	subtotal numeric(19, 2) NULL,
	city_id int8 NULL,
	payment_id int8 NOT NULL,
	restaurant_id int8 NOT NULL,
	users_id int8 NOT NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (id)
);

-- public.orders foreign keys

ALTER TABLE public.orders ADD CONSTRAINT fkag8ppnkjvx255gj7lm3m18wkj FOREIGN KEY (payment_id) REFERENCES public.payment(id);
ALTER TABLE public.orders ADD CONSTRAINT fke6k45xxoin4fylnwg2jkehwjf FOREIGN KEY (users_id) REFERENCES public.users(id);
ALTER TABLE public.orders ADD CONSTRAINT fki7hgjxhw21nei3xgpe4nnpenh FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(id);
ALTER TABLE public.orders ADD CONSTRAINT fkj1kdsxod2g3og10lca2wmpr99 FOREIGN KEY (city_id) REFERENCES public.city(id);