# MyBooking
Booking Resource

Spring Boot webapp for booking a resource
Use Thymeleaf, Bootstrap, AngularJS

Base functionality is complete.

with "prod" maven & spring profile for docker and MariaDB)

Pre:
create new Docker network: booking-net
create: /var/storage/docker/mysql-datadir for save mariaDB data file

mvn clean install docker:build -P prod
mvn docker:start -P prod

Compile and run app on docker!


Note:
For use application you have to configure a smtp server on configuration. For local usage i use smtpFake
