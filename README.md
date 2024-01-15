# PRICE CONSULTATION SERVICE

![](https://img.shields.io/badge/build-success-brightgreen.svg)

# Stack

![](https://img.shields.io/badge/java_17-✓-blue.svg)
![](https://img.shields.io/badge/spring_boot-✓-blue.svg)
![](https://img.shields.io/badge/h2_database-✓-blue.svg)
![](https://img.shields.io/badge/openapi_3-✓-blue.svg)

# Summary

Este servicio permite consultar el precio de un producto en una fecha determinada. Aca dejo un model ER de la solucion.

![](./ER-PRICE-PRODUCT.jpg)

# How to use this code?

1. Debe tener instalado [Java 17](https://www.java.com/download/) y [Maven](https://maven.apache.org)

2. Clonar este repositorio

```
$ git clone https://github.com/japarejodiaz/ms-prices-product.git
```

3. Posicionarse en la carpeta

```
$ cd ms-prices-product
```

4. Instalar dependencias

```
$ mvn install
```

5. Ejecutar el proyecto

```
$ mvn spring-boot:run
```

# Interfaces UI

Una vez iniciado el servicio puede acceder las siguientes UI:

### Swagger:
```
http://localhost:8080/swagger-ui/index.html?configUrl=/api-docs/swagger-config#/
```

### H2 console:

```
http://localhost:8080/h2-ui
```
Parametros necesarios

**JDBC-URL:** jdbc:h2:file:./products

**user-name:** sa

# Initial loading of data and tests - data de pruebas

### Prices table:

| PRICE_ID | BRAND_ID | PRODUCT_ID | CURRENCY_ID | START_DATE             | END_DATE               | PRICE_LIST | PRIORITY | PRICE |
|----------|----------|------------|------------|------------------------|------------------------|------------|----------|-------|
| 1        | 1        | 35455      | 2          | '2020-06-14T00:00:00'  | '2020-12-31T23:59:59'  | 1          | 0        | 35.50 |
| 2        | 1        | 35455      | 2          | '2020-06-14T15:00:00'  | '2020-06-14T18:30:00'  | 2          | 1        | 25.45 |
| 3        | 1        | 35455      | 2          | '2020-06-15T00:00:00'  | '2020-06-15T11:00:00'  | 3          | 1        | 30.50 |
| 4        | 1        | 35455      | 2          | '2020-06-15T16:00:00'  | '2020-12-31T23:59:59'  | 4          | 1        | 38.95 |
| 5        | 1        | 35455      | 2          | '2021-01-01T00:00:00'  | '2023-01-15T15:59:59'  | 4          | 1        | 40.95 |
| 6        | 1        | 35455      | 2          | '2023-01-15T16:00:00'  | '2023-12-31T23:59:59'  | 4          | 1        | 45.95 |
| 7        | 2        | 35456      | 2          | '2023-01-01T00:00:00'  | '2023-12-31T00:00:00'  | 2          | 1        | 150.00 |
| 8        | 3        | 35457      | 3          | '2023-01-01T00:00:00'  | '2023-12-31T00:00:00'  | 1          | 2        | 120.00 |

### Test cases:

-          Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 
-          Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 
-          Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 
-          Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 
-          Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 

### Results - resultados de las test
```
Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
2024-01-14T23:16:17.204-03:00  INFO 1520919 --- [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2024-01-14T23:16:17.204-03:00  INFO 1520919 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2024-01-14T23:16:17.210-03:00  INFO 1520919 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 3 ms
2024-01-14T23:16:17.290-03:00  INFO 1520919 --- [           main] e.c.p.b.service.impl.PricesServiceImpl   : Entering getPriceByProductAndBrandAndDate [brandId]: 1, [productId]: 35455, [consultationDate]: 2020-06-14T10:00
2024-01-14T23:16:17.526-03:00  INFO 1520919 --- [           main] e.c.p.b.service.impl.PricesServiceImpl   : Leaving getPriceByProductAndBrandAndDate [response]: PricesResponse(product=ProductDto(id=35455, name=JEANS BALLOON FIT), brand=BrandDto(id=1, name=Zara), priceList=1, startDate=2020-06-14T00:00, endDate=2020-12-31T23:59:59, price=35.5, currency=CurrencyDto(id=2, name=Euro, iso=EUR))
2024-01-14T23:16:17.737-03:00  INFO 1520919 --- [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2024-01-14T23:16:17.738-03:00  INFO 1520919 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2024-01-14T23:16:17.740-03:00  INFO 1520919 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 2 ms
2024-01-14T23:16:17.742-03:00  INFO 1520919 --- [           main] e.c.p.b.service.impl.PricesServiceImpl   : Entering getPriceByProductAndBrandAndDate [brandId]: 1, [productId]: 35455, [consultationDate]: 2020-06-14T16:00
2024-01-14T23:16:17.754-03:00  INFO 1520919 --- [           main] e.c.p.b.service.impl.PricesServiceImpl   : Leaving getPriceByProductAndBrandAndDate [response]: PricesResponse(product=ProductDto(id=35455, name=JEANS BALLOON FIT), brand=BrandDto(id=1, name=Zara), priceList=2, startDate=2020-06-14T15:00, endDate=2020-06-14T18:30, price=25.45, currency=CurrencyDto(id=2, name=Euro, iso=EUR))
2024-01-14T23:16:17.770-03:00  INFO 1520919 --- [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2024-01-14T23:16:17.770-03:00  INFO 1520919 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2024-01-14T23:16:17.772-03:00  INFO 1520919 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 2 ms
2024-01-14T23:16:17.774-03:00  INFO 1520919 --- [           main] e.c.p.b.service.impl.PricesServiceImpl   : Entering getPriceByProductAndBrandAndDate [brandId]: 1, [productId]: 35455, [consultationDate]: 2020-06-14T21:00
2024-01-14T23:16:17.786-03:00  INFO 1520919 --- [           main] e.c.p.b.service.impl.PricesServiceImpl   : Leaving getPriceByProductAndBrandAndDate [response]: PricesResponse(product=ProductDto(id=35455, name=JEANS BALLOON FIT), brand=BrandDto(id=1, name=Zara), priceList=1, startDate=2020-06-14T00:00, endDate=2020-12-31T23:59:59, price=35.5, currency=CurrencyDto(id=2, name=Euro, iso=EUR))
2024-01-14T23:16:17.804-03:00  INFO 1520919 --- [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2024-01-14T23:16:17.804-03:00  INFO 1520919 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2024-01-14T23:16:17.805-03:00  INFO 1520919 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms
2024-01-14T23:16:17.807-03:00  INFO 1520919 --- [           main] e.c.p.b.service.impl.PricesServiceImpl   : Entering getPriceByProductAndBrandAndDate [brandId]: 1, [productId]: 35455, [consultationDate]: 2020-06-15T10:00
2024-01-14T23:16:17.818-03:00  INFO 1520919 --- [           main] e.c.p.b.service.impl.PricesServiceImpl   : Leaving getPriceByProductAndBrandAndDate [response]: PricesResponse(product=ProductDto(id=35455, name=JEANS BALLOON FIT), brand=BrandDto(id=1, name=Zara), priceList=3, startDate=2020-06-15T00:00, endDate=2020-06-15T11:00, price=30.5, currency=CurrencyDto(id=2, name=Euro, iso=EUR))
2024-01-14T23:16:17.835-03:00  INFO 1520919 --- [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2024-01-14T23:16:17.835-03:00  INFO 1520919 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2024-01-14T23:16:17.837-03:00  INFO 1520919 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 2 ms
2024-01-14T23:16:17.839-03:00  INFO 1520919 --- [           main] e.c.p.b.service.impl.PricesServiceImpl   : Entering getPriceByProductAndBrandAndDate [brandId]: 1, [productId]: 35455, [consultationDate]: 2020-06-16T21:00
2024-01-14T23:16:17.849-03:00  INFO 1520919 --- [           main] e.c.p.b.service.impl.PricesServiceImpl   : Leaving getPriceByProductAndBrandAndDate [response]: PricesResponse(product=ProductDto(id=35455, name=JEANS BALLOON FIT), brand=BrandDto(id=1, name=Zara), priceList=4, startDate=2020-06-15T16:00, endDate=2020-12-31T23:59:59, price=38.95, currency=CurrencyDto(id=2, name=Euro, iso=EUR))

```
