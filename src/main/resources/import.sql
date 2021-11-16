insert into kitchen (id, name) values (1, 'Japonesa');
insert into kitchen (id, name) values (2, 'Tailandesa');

insert into restaurant (name, freight, kitchen_id) values ('Paraiso Tropical', 100, 1);
insert into restaurant (name, freight, kitchen_id) values ('Madri', 10, 2);

insert into payment_method (id, name) values (1, 'CASH');
insert into payment_method (id, name) values (2, 'CREDIT CARD');

insert into state (id, name) values (1, 'Bahia');

insert into city (id, name, state_id) values (1, 'Ubaíra', 1);
insert into city (id, name, state_id) values (2, 'Salvador', 1);

insert into permission (id, description, name) values (1, 'Administrators', 'admin');
