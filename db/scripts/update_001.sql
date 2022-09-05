create table if not exists auto_user (
   id serial primary key,
   login text,
   password text
);

create table if not exists auto_post (
   id serial primary key,
   text text,
   created timestamp,
   auto_user_id int references auto_user (id)
);