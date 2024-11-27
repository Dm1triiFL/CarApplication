-- Таблица моделей автомобилей
CREATE TABLE car_model (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    brand VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    country_origin VARCHAR(50),
    country_code VARCHAR(10)
);

-- Таблица дилеров
CREATE TABLE dealership (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(255)
);

-- Таблица автомобилей
CREATE TABLE car (
    id INT PRIMARY KEY AUTO_INCREMENT,
    car_model_id BIGINT,
    dealership_id BIGINT,
    state VARCHAR(20),
    configuration VARCHAR(100),
    color VARCHAR(20),
    price DECIMAL(10, 2),
    FOREIGN KEY (car_model_id) REFERENCES car_model(id),
    FOREIGN KEY (dealership_id) REFERENCES dealership(id)
);
