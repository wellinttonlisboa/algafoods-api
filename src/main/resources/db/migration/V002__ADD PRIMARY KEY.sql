--
-- Indices para tabelas despejadas
--

--
-- Indices para tabela city
--
ALTER TABLE city
  ADD PRIMARY KEY (id),
  ADD KEY FK6p2u50v8fg2y0js6djc6xanit (state_id);

--
-- Indices para tabela groups
--
ALTER TABLE groups
  ADD PRIMARY KEY (id);

--
-- Indices para tabela group_permission
--
ALTER TABLE groups_permission
  ADD PRIMARY KEY (group_id,permission_id),
  ADD KEY FKq5xwkpxmmgji5tt5x7feywwqq (permission_id);

--
-- Indices para tabela kitchen
--
ALTER TABLE kitchen
  ADD PRIMARY KEY (id),
  ADD UNIQUE KEY UK_et2asvv42fvkrhe2k0urd05pd (name);

--
-- Indices para tabela payment_method
--
ALTER TABLE payment_method
  ADD PRIMARY KEY (id);

--
-- Indices para tabela permission
--
ALTER TABLE permission
  ADD PRIMARY KEY (id);

--
-- Indices para tabela product
--
ALTER TABLE product
  ADD PRIMARY KEY (id),
  ADD KEY FKp4b7e36gck7975p51raud8juk (restaurant_id),
  ADD KEY FK8556hocjcb04st51nt8yknfbg (status_id);

--
-- Indices para tabela restaurant
--
ALTER TABLE restaurant
  ADD PRIMARY KEY (id),
  ADD UNIQUE KEY UK_i6u3x7opncroyhd755ejknses (name),
  ADD KEY FK8pcwgn41lfg43d8u82ewn3cn (address_city_id),
  ADD KEY FKrur1dqx79jim8s8dvdp16qc3d (kitchen_id);

--
-- Indices para tabela restaurant_payment
--
ALTER TABLE restaurant_payment
  ADD PRIMARY KEY (restaurant_id,payment_id),
  ADD KEY FK1piivrr6xm3fjvpcbux7rkswl (payment_id);

--
-- Indices para tabela restaurant_products
--
ALTER TABLE restaurant_product
  ADD PRIMARY KEY (restaurant_id,product_id),
  ADD KEY FKbl3gpoj5hq1lc0q9ue7psvqch (product_id);

--
-- Indices para tabela state
--
ALTER TABLE state
  ADD PRIMARY KEY (id);

--
-- Indices para tabela status
--
ALTER TABLE status
  ADD PRIMARY KEY (id);

--
-- Indices para tabela user
--
ALTER TABLE user
  ADD PRIMARY KEY (id);

--
-- Indices para tabela user_group
--
ALTER TABLE user_groups
  ADD PRIMARY KEY (user_id,group_id),
  ADD KEY FKp2p74bdhj5bso5tr0smnjlbhn (group_id);