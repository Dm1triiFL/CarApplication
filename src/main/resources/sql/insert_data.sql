-- Добавление записей в таблицу моделей автомобилей
INSERT INTO car_model (brand, model, country_origin, country_code) VALUES
('Toyota', 'Corolla', 'Japan', 'JP'),
('Ford', 'Focus', 'USA', 'US'),
('Volkswagen', 'Golf', 'Germany', 'DE');

-- Добавление записей в таблицу дилеров
INSERT INTO dealership (name, address) VALUES
('Дилер 1', 'ул. Ленина, 12'),
('Дилер 2', 'ул. Победы, 24');

-- Добавление записей в таблицу автомобилей
INSERT INTO car (car_model_id, dealership_id, state, configuration, color, price) VALUES
(1, 1, 'new', 'standard', 'red', 15000),
(2, 2, 'used', 'premium', 'blue', 10000);
