-- public.photo definition

-- Drop table

-- DROP TABLE public.photo;

CREATE TABLE public.photo (
	content_type varchar(255) NULL,
	description varchar(255) NULL,
	file_name varchar(255) NULL,
	"size" int8 NULL,
	product_id int8 NOT NULL,
	CONSTRAINT photo_pkey PRIMARY KEY (product_id)
);


-- public.photo foreign keys

ALTER TABLE public.photo ADD CONSTRAINT fk8hs00tlacip0319kutudailre FOREIGN KEY (product_id) REFERENCES public.product(id);