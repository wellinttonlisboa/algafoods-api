--
-- Restricoes para despejos de tabelas
--

--
-- Limitadores para a tabela city
--
ALTER TABLE city
  ADD CONSTRAINT FK6p2u50v8fg2y0js6djc6xanit FOREIGN KEY (state_id) REFERENCES state (id);

--
-- Limitadores para a tabela group_permission
--
ALTER TABLE groups_permission
  ADD CONSTRAINT FK3mc8dsxrdu2uyvq247b42xoe7 FOREIGN KEY (group_id) REFERENCES groups (id),
  ADD CONSTRAINT FKq5xwkpxmmgji5tt5x7feywwqq FOREIGN KEY (permission_id) REFERENCES permission (id);

--
-- Limitadores para a tabela product
--
ALTER TABLE product
  ADD CONSTRAINT FK8556hocjcb04st51nt8yknfbg FOREIGN KEY (status_id) REFERENCES status (id),
  ADD CONSTRAINT FKp4b7e36gck7975p51raud8juk FOREIGN KEY (restaurant_id) REFERENCES restaurant (id);
  
--
-- Limitadores para a tabela restaurant
--
ALTER TABLE restaurant
  ADD CONSTRAINT FK8pcwgn41lfg43d8u82ewn3cn FOREIGN KEY (address_city_id) REFERENCES city (id),
  ADD CONSTRAINT FKrur1dqx79jim8s8dvdp16qc3d FOREIGN KEY (kitchen_id) REFERENCES kitchen (id);

--
-- Limitadores para a tabela restaurant_payment
--
ALTER TABLE restaurant_payment
  ADD CONSTRAINT FK1piivrr6xm3fjvpcbux7rkswl FOREIGN KEY (payment_id) REFERENCES payment_method (id),
  ADD CONSTRAINT FKa22yraajjo2r7mbpvetv14mfx FOREIGN KEY (restaurant_id) REFERENCES restaurant (id);

--
-- Limitadores para a tabela restaurant_product
--
ALTER TABLE restaurant_product
  ADD CONSTRAINT FKbl3gpoj5hq1lc0q9ue7psvqch FOREIGN KEY (product_id) REFERENCES product (id),
  ADD CONSTRAINT FKs05j37liggvxhfaqeh5wjr2dd FOREIGN KEY (restaurant_id) REFERENCES restaurant (id);

--
-- Limitadores para a tabela user_groups
--
ALTER TABLE user_groups
  ADD CONSTRAINT FKp2p74bdhj5bso5tr0smnjlbhn FOREIGN KEY (group_id) REFERENCES groups (id),
  ADD CONSTRAINT FKxgk67l5yp8458l39rog6nppe FOREIGN KEY (user_id) REFERENCES user (id);