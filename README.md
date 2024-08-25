<h1 align="center">Консольное приложение, реализующее функционал формирования чека в магазине</h1>

<details>
 <summary><strong>
  Техническое задание
</strong></summary>

#### ЗАДАНИЕ:

#### Тестовое задание 3:

* В качестве DB использовать PostgreSQL.
* Разрешено использовать только JDBC (org.postgresql.Driver).
* Замена хранения исходных данных в файлах на хранение в таблицах PostgreSQL: product и discount_card.
* Убран параметр pathToFile
* Настройки подключения к БД передавать через аргументы командной строки на вход добавлены параметры: (Обязательные)
datasource.url=ххх datasource.username=ххх datasource.password=ххх
* Пример:
* java -jar clevertec-check.jar 3-1 2-5 5-1 discountCard=1111 balanceDebitCard=100 saveToFile=./result.csv
datasource.url=jdbc:postgresql://localhost:5432/check datasource.username=postgres datasource.password=postgres
* DDL/DML операции должны храниться в файле src/main/resources/data.sql (но не использовать)
* Покрыть функционал юнит-тестами (не менее 70 %).
* Исходники разместить в присланном репозитории в ветке feature/entry-database
</details>

<details>
 <summary><strong>
  Стек
</strong></summary>

#### При разработке были использованы:

* Java 21
* Gradle 8.5

</details>

<details>
 <summary><strong>
  Запуск проекта
</strong></summary>

* Необходимо создать базу данных PostgreSQL имеющую название check.
* Скачайте проект с gitHub:
    * https://github.com/Nikolay1992167/ClevertecCheck.git
* Откройте терминал или командную строку и перейдите в директорию вашего проекта
* Соберите jar-файл с помощью комманды сборщика ```gradle build```
* Затем в командной строке перейти в директорию build/libs, где расположен clevertec-check.jar выполняем команду старта 
* приложения.
</details>

<details>
 <summary><strong>
  Валидные команды
</strong></summary>

* java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-1 2-2 3-3 discountCard=1111 balanceDebitCard=100.01 
saveToFile=./result.csv datasource.url=jdbc:postgresql://localhost:5432/check datasource.username=postgres 
datasource.password=87654321
* Username and password установите в соответствии  с вашими данными.
</details>

<details>
 <summary><strong>
  Команды с ошибками
</strong></summary>

* Не достаточно средств (NOT ENOUGH MONEY):
*  java -jar clevertec-check.jar 3-1 2-5 5-1 discountCard=1111 balanceDebitCard=1 saveToFile=./error_result.csv 
datasource.url=jdbc:postgresql://localhost:5432/check datasource.username=postgres datasource.password=87654321

* Не передан баланс карты (BAD REQUEST):
* java -jar clevertec-check.jar 3-1 2-5 5-1 discountCard=1111 saveToFile=./error_result.csv 
* datasource.url=jdbc:postgresql://localhost:5432/check datasource.username=postgres datasource.password=87654321

* Не передано ни одного продукта (BAD REQUEST):
* java -jar clevertec-check.jar discountCard=1111 balanceDebitCard=100 saveToFile=./error_result.csv datasource.url=jdbc:postgresql://localhost:5432/check datasource.username=postgres datasource.password=87654321

* Не переданы данные для подключения к базе данных:
* java -jar clevertec-check.jar 3-1 2-5 5-1 discountCard=1111 balanceDebitCard=100 saveToFile=./result.csv
</details>

<details>
 <summary><strong>
  Тесты
</strong></summary>

* Тестами покрыто 70% классов согласно Coverage.
* Запустить тесты можно с помощью команды ```./gradlew test```.
</details>