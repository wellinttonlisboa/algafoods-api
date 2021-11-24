--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela city
--
ALTER TABLE city
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela groups
--
ALTER TABLE groups
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela kitchen
--
ALTER TABLE kitchen
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela payment_method
--
ALTER TABLE payment_method
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela permission
--
ALTER TABLE permission
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela product
--
ALTER TABLE product
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela restaurant
--
ALTER TABLE restaurant
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela state
--
ALTER TABLE state
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela status
--
ALTER TABLE status
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela user
--
ALTER TABLE user
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;