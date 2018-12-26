CREATE TABLE public.groupitem
(
    id serial PRIMARY KEY NOT NULL,
    name varchar,
    logist int,
    CONSTRAINT logist FOREIGN KEY (logist) REFERENCES public.tablelogist (id) DEFERRABLE INITIALLY DEFERRED
);
CREATE UNIQUE INDEX groupitem_id_uindex ON public.groupitem (id);