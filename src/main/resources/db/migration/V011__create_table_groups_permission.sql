CREATE TABLE public.groups_permission (
	groups_id int8 NOT NULL,
	permission_id int8 NOT NULL
);
-- public.groups_permission foreign keys

ALTER TABLE public.groups_permission ADD CONSTRAINT fk3mc8dsxrdu2uyvq247b42xoe7 FOREIGN KEY (groups_id) REFERENCES public."groups"(id);
ALTER TABLE public.groups_permission ADD CONSTRAINT fkq5xwkpxmmgji5tt5x7feywwqq FOREIGN KEY (permission_id) REFERENCES public."permission"(id);