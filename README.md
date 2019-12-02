# CurrencyCalculator


# To run 
### With maven

Run mysql on localhost:3306 <br>
change database credentials in application.properties (default root/rootpw)

```
./mvnw clean install
./mvnw spring-boot:run
```

### With docker-compose
```
docker-compose up
```

# Usage
list available currencies
```
curl localhost:8080/api/list
```
list currencies rates
```
curl localhost:8080/api/rates
```
convert currency (parameters @from @to @value)
```
curl localhost:8080/api/convert?from=usd&to=eur&value=500
```
