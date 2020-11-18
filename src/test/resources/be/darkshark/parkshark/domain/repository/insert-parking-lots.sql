INSERT INTO employee (id, city, house_number, postal_code, street, first_name, last_name, mail_address, country_code, phone_number, mobile_country_code, mobile_phone_number)
VALUES (1, 'Lokeren', '1', 9160, 'some street', 'Jeroen', 'De Man','some@mail.com', '+32', 477889911, '+32', 477889911);

INSERT INTO division(id, name, original_name, director, parent_division)
VALUES (1, 'testname', 'testoriginalname', 1, null);

INSERT INTO parkinglot(id, name, category, capacity, contact_person, city, house_number, postal_code, street, price_per_hour, division)
VALUES (1, 'parkinglot1', 1, 5, 1, 'Lokeren', '1', 9160, 'some street', 22.22, 1);
