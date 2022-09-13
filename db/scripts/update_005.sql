create table if not exists engine (
   id serial primary key,
   name text
);

create table if not exists car (
   id serial primary key,
   name text,
   engine_id int not null references engine(id)
);

create table if not exists driver (
   id serial primary key,
   text text,
   user_id int references auto_user(id)
);

create table if not exists history_owner (
   id serial PRIMARY KEY,
   car_id int references auto_user(id),
   driver_id int references driver(id),
   startAt timestamp,
   endAt timestamp
);

create table if not exists auto_post (
   id serial primary key,
   text text,
   created timestamp,
   photo bytea,
   auto_user_id int references auto_user (id),
   car_id int references car(id),
   price_history int references PRICE_HISTORY(id)
);