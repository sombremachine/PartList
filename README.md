# PartList
JavaRush test task

Проект собирается в .war командой mvn clean package
Или mvn spring-boot:run запускает экземпляр tomcat и разворачивает там приложение.

Таблица в БД пересоздается при старте из-за параметра spring.jpa.hibernate.ddl-auto = create в application.properties, и каждый раз заполняется тестовыми данными из data.sql (spring.datasource.initialization-mode=always)
