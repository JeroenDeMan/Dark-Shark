-- create table person ( id bigint not null, city varchar(255), house_number varchar(255), postal_code varchar(255),
-- street varchar(255), first_name varchar(255), last_name varchar(255), mail_address varchar(255), country_code varchar(255), phone_number integer not null,
--  license_country varchar(255), license_number varchar(255), member_ship_level integer, registration_date date)


insert into member (id, city, house_number, postal_code, street, first_name, last_name, mail_address, country_code,phone_number, license_country, license_number,member_ship_level, registration_date)
values (1, 'Lokeren', '1', 9160, 'some street', 'Jeroen', 'De Man','some@mail.com', '+32', 477889911, 'Belgium', '010-aba', 1, to_date('17-11-2020', ('dd-MM-yyyy')));
