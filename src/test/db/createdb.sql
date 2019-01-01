create table if not exists some_table (id int primary key, some_value varchar(255) not null);
delete from some_table;
insert into some_table values (1, 'from_database');
