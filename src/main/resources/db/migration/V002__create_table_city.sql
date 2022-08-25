
CREATE TABLE public.city (
	id bigint NOT NULL,
	"name" varchar(255) NOT NULL,
	state_id int8 NULL,
	CONSTRAINT city_pkey PRIMARY KEY (id)
);

-- public.city foreign keys

ALTER TABLE public.city ADD CONSTRAINT fk6p2u50v8fg2y0js6djc6xanit FOREIGN KEY (state_id) REFERENCES public.state(id);