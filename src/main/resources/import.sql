insert into kitchen (id, name) values (1, 'Japonesa');
insert into kitchen (id, name) values (2, 'Tailandesa');

insert into state (id, name) values (1, 'Bahia');

insert into city (id, name, state_id) values (1, 'Ubaíra', 1);
insert into city (id, name, state_id) values (2, 'Salvador', 1);

insert into restaurant (id, address_complement, address_number, address_public_place, address_zip_code, freight, name, address_city_id, kitchen_id, created_at, updated_at) values (1, 'QUERO BAHIA', '135', 'RUA DUARTE GUIMARÃES', '45310000', 100, 'Paraiso Tropical', 1, 1, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, freight, kitchen_id, created_at, updated_at) values (2, 'Madri', 10, 2, utc_timestamp, utc_timestamp);

insert into payment_method (id, name) values (1, 'CASH');
insert into payment_method (id, name) values (2, 'CREDIT CARD');

insert into permission (id, description, name) values (1, 'Administrators', 'admin');

insert into restaurant_payments (restaurant_id, payments_id) values (1, 1), (1, 2), (2, 1);
