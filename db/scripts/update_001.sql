create table if not exists auto_user (
   id serial primary key,
   login text,
   password text
);

CREATE TABLE PRICE_HISTORY(
   id SERIAL PRIMARY KEY,
   before BIGINT not null,
   after BIGINT not null,
   created TIMESTAMP WITHOUT TIMEZONE DEFAULT now()
);

create table if not exists auto_post (
   id serial primary key,
   text text,
   created timestamp,
   auto_user_id int references auto_user (id),
   price_history int references PRICE_HISTORY(id)
);