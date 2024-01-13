# PRICE CONSULTATION SERVICE

![](https://img.shields.io/badge/build-success-brightgreen.svg)

# Stack

![](https://img.shields.io/badge/java_17-✓-blue.svg)
![](https://img.shields.io/badge/spring_boot-✓-blue.svg)
![](https://img.shields.io/badge/h2_database-✓-blue.svg)
![](https://img.shields.io/badge/openapi_3-✓-blue.svg)

# Summary

Este servicio permite consultar el precio de un producto en una fecha determinada. Aca dejo un DER de la solucion.

![](./DER-PRICE.jpg)

# How to use this code?

1. Debe tener instalado [Java 17](https://www.java.com/download/) y [Maven](https://maven.apache.org)

2. Clonar este repositorio

```
$ git clone https://github.com/darwinmartinez86/ms-prices
```

3. Posicionarse en la carpeta

```
$ cd ms-prices
```

4. Instalar depencencias

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

# Initial loading of data and tests

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

### Results
```
2023-10-22T13:44:36.286-03:00  INFO 23714 --- [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2023-10-22T13:44:36.287-03:00  INFO 23714 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2023-10-22T13:44:36.289-03:00  INFO 23714 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms
2023-10-22T13:44:36.327-03:00 DEBUG 23714 --- [           main] e.c.b.service.impl.PricesServiceImpl     : Entering getPriceByProductAndBrandAndDate [brandId]: 1, [productId]: 35455, [consultationDate]: 2020-06-14T10:00
2023-10-22T13:44:36.475-03:00 DEBUG 23714 --- [           main] e.c.b.service.impl.PricesServiceImpl     : Leaving getPriceByProductAndBrandAndDate [response]: PricesResponse(product=ProductDto(id=35455, name=JEANS BALLOON FIT), brand=BrandDto(id=1, name=Zara), priceList=1, startDate=2020-06-14T00:00, endDate=2020-12-31T23:59:59, price=35.5, currency=CurrencyDto(id=2, name=Euro, iso=EUR))
2023-10-22T13:44:36.572-03:00  INFO 23714 --- [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2023-10-22T13:44:36.573-03:00  INFO 23714 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2023-10-22T13:44:36.573-03:00  INFO 23714 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
2023-10-22T13:44:36.575-03:00 DEBUG 23714 --- [           main] e.c.b.service.impl.PricesServiceImpl     : Entering getPriceByProductAndBrandAndDate [brandId]: 1, [productId]: 35455, [consultationDate]: 2020-06-14T16:00
2023-10-22T13:44:36.581-03:00 DEBUG 23714 --- [           main] e.c.b.service.impl.PricesServiceImpl     : Leaving getPriceByProductAndBrandAndDate [response]: PricesResponse(product=ProductDto(id=35455, name=JEANS BALLOON FIT), brand=BrandDto(id=1, name=Zara), priceList=2, startDate=2020-06-14T15:00, endDate=2020-06-14T18:30, price=25.45, currency=CurrencyDto(id=2, name=Euro, iso=EUR))
2023-10-22T13:44:36.584-03:00  INFO 23714 --- [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2023-10-22T13:44:36.584-03:00  INFO 23714 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2023-10-22T13:44:36.585-03:00  INFO 23714 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms
2023-10-22T13:44:36.586-03:00 DEBUG 23714 --- [           main] e.c.b.service.impl.PricesServiceImpl     : Entering getPriceByProductAndBrandAndDate [brandId]: 1, [productId]: 35455, [consultationDate]: 2020-06-14T21:00
2023-10-22T13:44:36.590-03:00 DEBUG 23714 --- [           main] e.c.b.service.impl.PricesServiceImpl     : Leaving getPriceByProductAndBrandAndDate [response]: PricesResponse(product=ProductDto(id=35455, name=JEANS BALLOON FIT), brand=BrandDto(id=1, name=Zara), priceList=1, startDate=2020-06-14T00:00, endDate=2020-12-31T23:59:59, price=35.5, currency=CurrencyDto(id=2, name=Euro, iso=EUR))
2023-10-22T13:44:36.592-03:00  INFO 23714 --- [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2023-10-22T13:44:36.592-03:00  INFO 23714 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2023-10-22T13:44:36.593-03:00  INFO 23714 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms
2023-10-22T13:44:36.594-03:00 DEBUG 23714 --- [           main] e.c.b.service.impl.PricesServiceImpl     : Entering getPriceByProductAndBrandAndDate [brandId]: 1, [productId]: 35455, [consultationDate]: 2020-06-15T10:00
2023-10-22T13:44:36.600-03:00 DEBUG 23714 --- [           main] e.c.b.service.impl.PricesServiceImpl     : Leaving getPriceByProductAndBrandAndDate [response]: PricesResponse(product=ProductDto(id=35455, name=JEANS BALLOON FIT), brand=BrandDto(id=1, name=Zara), priceList=3, startDate=2020-06-15T00:00, endDate=2020-06-15T11:00, price=30.5, currency=CurrencyDto(id=2, name=Euro, iso=EUR))
2023-10-22T13:44:36.601-03:00  INFO 23714 --- [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2023-10-22T13:44:36.602-03:00  INFO 23714 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2023-10-22T13:44:36.602-03:00  INFO 23714 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 0 ms
2023-10-22T13:44:36.603-03:00 DEBUG 23714 --- [           main] e.c.b.service.impl.PricesServiceImpl     : Entering getPriceByProductAndBrandAndDate [brandId]: 1, [productId]: 35455, [consultationDate]: 2020-06-16T21:00
2023-10-22T13:44:36.609-03:00 DEBUG 23714 --- [           main] e.c.b.service.impl.PricesServiceImpl     : Leaving getPriceByProductAndBrandAndDate [response]: PricesResponse(product=ProductDto(id=35455, name=JEANS BALLOON FIT), brand=BrandDto(id=1, name=Zara), priceList=4, startDate=2020-06-15T16:00, endDate=2020-12-31T23:59:59, price=38.95, currency=CurrencyDto(id=2, name=Euro, iso=EUR))
2023-10-22T13:44:36.611-03:00  INFO 23714 --- [           main] o.s.b.t.m.w.SpringBootMockServletContext : Initializing Spring TestDispatcherServlet ''
2023-10-22T13:44:36.611-03:00  INFO 23714 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Initializing Servlet ''
2023-10-22T13:44:36.612-03:00  INFO 23714 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : Completed initialization in 1 ms
2023-10-22T13:44:36.612-03:00 DEBUG 23714 --- [           main] e.c.b.service.impl.PricesServiceImpl     : Entering getPriceByProductAndBrandAndDate [brandId]: 1, [productId]: 35455, [consultationDate]: null
2023-10-22T13:44:36.618-03:00 DEBUG 23714 --- [           main] e.c.b.service.impl.PricesServiceImpl     : Leaving getPriceByProductAndBrandAndDate [response]: PricesResponse(product=ProductDto(id=35455, name=JEANS BALLOON FIT), brand=BrandDto(id=1, name=Zara), priceList=4, startDate=2023-01-15T16:00, endDate=2023-12-31T23:59:59, price=45.95, currency=CurrencyDto(id=2, name=Euro, iso=EUR))

```
