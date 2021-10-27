set schema public;
create table if not exists fibonachi
(
    id     int primary key auto_increment not null,
    number int                            not null,
    result long                           not null
);
