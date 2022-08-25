CREATE TABLE public.restaurants_products (
	restaurant_id int8 NOT NULL,
	product_id int8 NOT NULL
);

-- public.restaurants_products foreign keys

ALTER TABLE public.restaurants_products ADD CONSTRAINT fkscb8gdwpwgnx8maqyb4976wug FOREIGN KEY (product_id) REFERENCES public.product(id);