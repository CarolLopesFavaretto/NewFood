CREATE TABLE public.restaurants_user_responsible (
	restaurant_id int8 NOT NULL,
	user_id int8 NOT NULL,
	CONSTRAINT restaurants_user_responsible_pkey PRIMARY KEY (restaurant_id, user_id)
);

-- public.restaurants_user_responsible foreign keys

ALTER TABLE public.restaurants_user_responsible ADD CONSTRAINT fkaie19mtd8spabda8egngdi860 FOREIGN KEY (user_id) REFERENCES public.users(id);
ALTER TABLE public.restaurants_user_responsible ADD CONSTRAINT fkrqg1m7kh0trvkavvggqkw03wr FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(id);