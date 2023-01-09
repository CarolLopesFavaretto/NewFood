alter table public.restaurant add column open boolean;
update restaurant set open = false;