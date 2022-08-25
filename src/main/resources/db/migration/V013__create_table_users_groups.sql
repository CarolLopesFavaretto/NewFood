CREATE TABLE public.users_groups (
	users_id int8 NOT NULL,
	groups_id int8 NOT NULL
);
-- public.users_groups foreign keys

ALTER TABLE public.users_groups ADD CONSTRAINT fkeg984vk9mx0imcdffn06f8q45 FOREIGN KEY (users_id) REFERENCES public.users(id);
ALTER TABLE public.users_groups ADD CONSTRAINT fkjex8no6gj9undclnlyn9l52wm FOREIGN KEY (groups_id) REFERENCES public."groups"(id);