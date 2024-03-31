## Warehouse Manager CRUD API

## Инструкция по запуску

### I. Запуск локально с in-memory БД (H2 database)

Создается локальная БД, в которой уже будут некоторые данные

1. Измените свойства в `warehouse-manager/src/main/resources/application.yml`:
```yaml
spring:
    datasource:
        url: jdbc:h2:mem:testdb;NON_KEYWORDS=value
        driver-class-name: org.h2.Driver
...
```

2. Запустите `mvn spring-boot:run` из папки `warehouse-manager`

### II. Запуск локально с PostgreSQL БД

1. Создайте БД `mediasoft_db`

2. Создайте таблицу следующим запросом:
```postgresql
CREATE TABLE product (
                         id UUID PRIMARY KEY,
                         name VARCHAR NOT NULL,
                         article VARCHAR UNIQUE NOT NULL,
                         description VARCHAR NOT NULL,
                         price NUMERIC,
                         count INTEGER,
                         date_time_last_edit TIMESTAMP,
                         date_creation DATE,
                         category VARCHAR NOT NULL
);
```
3. Измените свойства в `warehouse-manager/src/main/resources/application.yml`:
```yaml
spring:
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/mediasoft_db
    username: ...
    password: ...
...
```

4. Запустите `mvn spring-boot:run` из папки `warehouse-manager`

## Try it out

Примеры всех запросов можно найти в Postman collection [Warehouse manager REST.postman_collection.json](extra/Warehouse manager REST.postman_collection.json)

Интерфейс Swagger с документацией OpenApi доступен для запуска в [/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Бизнес логика
1. Артикул у каждого продукта уникальный
2. Количество товара не может меньше 1
3. Цена не может быть отрицательной