CREATE TABLE public.tableitem
(
    code int PRIMARY KEY NOT NULL,
    name varchar NOT NULL,
    comment varchar,
    surrogate int,
    groupitem int,
    CONSTRAINT tableitem_groupitem_id_fk FOREIGN KEY (groupitem) REFERENCES public.groupitem (id) DEFERRABLE
);
CREATE UNIQUE INDEX tableitem_code_uindex ON public.tableitem (code);