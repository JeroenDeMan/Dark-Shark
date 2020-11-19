
-- Insert Member
insert into member (id, city, house_number, postal_code, street, first_name, last_name, mail_address, country_code,phone_number, license_country, license_number,member_ship_level, registration_date)
values (1, 'Lokeren', '1', 9160, 'some street', 'Jeroen', 'De Man','some@mail.com', '+32', 477889911, 'Belgium', '010-aba', 1, to_date('17-11-2020', ('dd-MM-yyyy')));

insert into member (id, city, house_number, postal_code, street, first_name, last_name, mail_address, country_code,phone_number, license_country, license_number,member_ship_level, registration_date)
values (2, 'Geraadsbergen', '1', 2000, 'Yet Another street', 'Pascal', 'Baelen','WhatMail@mail.com', '+32', 411223344, 'Belgium', '020-bab', 2, to_date('19-11-2020', ('dd-MM-yyyy')));

insert into member (id, city, house_number, postal_code, street, first_name, last_name, mail_address, country_code,phone_number, license_country, license_number,member_ship_level, registration_date)
values (3, 'Dendermonde', '1', 9200, 'street', 'Kris', 'Ceulemans','check@mail.com', '+32', 455661122, 'Belgium', '030-nen', 0, to_date('19-11-2020', ('dd-MM-yyyy')));


-- Insert Employee
INSERT INTO employee (id, city, house_number, postal_code, street, first_name, last_name, mail_address, country_code, phone_number, mobile_country_code, mobile_phone_number)
VALUES (1, 'Gent', '10', 9000, 'another street', 'Bram', 'Vandepoele','another@mail.com', '+32', 488991122, '+32', 477889911);

-- Insert Division
INSERT INTO division(id, name, original_name, director, parent_division)
VALUES (1, 'testname', 'testoriginalname', 1, null);

-- Insert ParkingLot
INSERT INTO parkinglot(id, name, category, capacity, contact_person, city, house_number, postal_code, street, price_per_hour, division)
VALUES (1, 'parkinglot1', 1, 5, 1, 'Lokeren', '1', 9160, 'some street', 22.22, 1);

INSERT INTO parkinglot(id, name, category, capacity, contact_person, city, house_number, postal_code, street, price_per_hour, division)
VALUES (2, 'parkinglot2', 0, 10, 1, 'Brussel', '10', 1000, 'switchfully', 40.00, 1);

-- Insert Allocation
insert into allocation (id, end_time, licence_plate, start_time, member_id, parking_lot_id)
values (1, null, '010-aba', parsedatetime('19-11-2020 10:47:52', 'dd-MM-yyyy hh:mm:ss'),1, 1 );

insert into allocation (id, end_time, licence_plate, start_time, member_id, parking_lot_id)
values (2, null, '020-bab', parsedatetime('19-11-2020 11:47:52', 'dd-MM-yyyy hh:mm:ss'),2, 1 );

insert into allocation (id, end_time, licence_plate, start_time, member_id, parking_lot_id)
values (3, parsedatetime('18-11-2020 10:47:52', 'dd-MM-yyyy hh:mm:ss'), '010-aba', parsedatetime('18-11-2020 11:47:52', 'dd-MM-yyyy hh:mm:ss'),1, 2 );

insert into allocation (id, end_time, licence_plate, start_time, member_id, parking_lot_id)
values (4, parsedatetime('18-11-2020 10:47:52', 'dd-MM-yyyy hh:mm:ss'), '010-aba', parsedatetime('18-11-2020 11:47:52', 'dd-MM-yyyy hh:mm:ss'),3, 2 );

