create table operation
(
    id_operation integer GENERATED BY DEFAULT AS IDENTITY not null,
    client_id bigint references public.client(id) not null,
    type_operation integer,
    amount numeric(38, 2),
    time_operation timestamp without time zone,
    CONSTRAINT pk_operation PRIMARY KEY (id_operation)
);

alter table operation
    owner to postgres;