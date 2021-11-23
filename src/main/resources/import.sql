insert into kitchen (id, name) values (1, 'Japonesa');
insert into kitchen (id, name) values (2, 'Tailandesa');

insert into state (id, name) values (1, 'Bahia');

insert into city (id, name, state_id) values (1, 'Ubaíra', 1);
insert into city (id, name, state_id) values (2, 'Salvador', 1);

insert into restaurant (id, address_complement, address_number, address_public_place, address_zip_code, freight, name, address_city_id, kitchen_id, created_at, updated_at) values (1, 'QUERO BAHIA', '135', 'RUA DUARTE GUIMARÃES', '45310000', 100, 'Paraiso Tropical', 1, 1, utc_timestamp, utc_timestamp);
insert into restaurant (id, name, freight, kitchen_id, created_at, updated_at) values (2, 'Madri', 10, 2, utc_timestamp, utc_timestamp);

insert into payment_method (id, name) values (1, 'CASH');
insert into payment_method (id, name) values (2, 'CREDIT CARD');

insert into permission (id, description, name) values (1, 'Create object', 'CREATE');

insert into groups (id, name) values (1, 'Administrators');

insert into groups_permission (groups_id, permission_id) values (1, 1);

insert into restaurant_payments (restaurant_id, payments_id) values (1, 1), (1, 2), (2, 1);

insert into status (id, state, description) values (1, true, 'Activate'), (2, false, 'Deactivate');

insert into product (id, name, description, price, status_id, restaurant_id) values (1, 'Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into product (id, name, description, price, status_id, restaurant_id) values (2, 'Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into product (id, name, description, price, status_id, restaurant_id) values (3, 'Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into product (id, name, description, price, status_id, restaurant_id) values (4, 'Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 2);
insert into product (id, name, description, price, status_id, restaurant_id) values (5, 'Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 2);
insert into product (id, name, description, price, status_id, restaurant_id) values (6, 'Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 1);
insert into product (id, name, description, price, status_id, restaurant_id) values (7, 'T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 1);
insert into product (id, name, description, price, status_id, restaurant_id) values (8, 'Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 2);
insert into product (id, name, description, price, status_id, restaurant_id) values (9, 'Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 2);

insert into user (id, name, email, password, created_at) value (1, 'Wellintton Lisboa', 'wlisboa@outlook.com', 'passw0rd', utc_timestamp);

insert into user_groups (user_id, groups_id) values (1, 1);

insert into restaurant_products (restaurant_id, products_id) value (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (2, 1), (2, 2), (2, 3), (2, 4), (2, 5);