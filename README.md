# CurrencyCalculator


# To run 
### With maven

Run mysql on localhost:3306 <br>
change database credentials in application.properties (default root/rootpw)

```
./mvnw clean install
./mvnw spring-boot:run
```meaven

### With docker-compose
```
docker-compose up
```
meaven
# Usage
list available currencies
```
http://localhost:8080/api/list
```
list currencies rates
```
http://localhost:8080/api/prices
```
convert currency
```
http://localhost:8080/api/calculate?from=usd&to=eur&value=500
```
