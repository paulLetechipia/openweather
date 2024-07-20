# OpenWeather
Proyecto que realiza el consumo del api openweather

# OpenWeather API
La url y el api key se encuentran definidos en el archivo properties del proyecto para poder ser consumido el api.

Para copiar el proyecto localmente ejecutar:

git clone -b master https://github.com/paulLetechipia/openweather.git

# Base de datos MSSQL
El proyecto trabaja con una base de datos mssql, para poder levantarla es necesario realizar los siguientes pasos en docker:
+docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=WeatherP455W0RD" -e "MSSQL_PID=Express" -p 1433:1433 -d mcr.microsoft.com/mssql/server:2019-latest

Una vez creada la imagen se deberá crear la base de datos:
CREATE DATABASE OpenWeatherDB;
USE OpenWeatherDB;
Las tablas se generarán automaticamente al levantar el proyecto.
Esta es la configuracion que se tiene dentro del proyecto para utilizar la base de datos:
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=OpenWeatherDB;encrypt=true;trustServerCertificate=true
spring.datasource.username=sa
spring.datasource.password=WeatherP455W0RD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServerDialect

# Levantar la aplicación
Para levantar la aplicación solo debe ejecutarse como una aplicación de spring boot desde su IDE correspondiente.
El puerto de la aplicación está configurado para levantarse en el 8082

# Endpoints de la aplicación
Para levantar la aplicación solo debe ejecutarse como una aplicación de spring boot desde su IDE correspondiente.
Se cuentan con 3 endpoints:
[/weatherCityPost](http://localhost:8082/weatherCityPost)
[/weatherCity](http://localhost:8082/weatherCity?city=?)
[/history](http://localhost:8082/history)

Para mayor información acerca de los mismos puede consultar la documentación realizada con swagger en la url:
http://localhost:8082/swagger-ui/index.html
