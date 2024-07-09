<h1 align="center">Консольное приложение, реализующее функционал формирования чека в магазине</h1>

<details>
 <summary><strong>
  Техническое задание
</strong></summary>

#### ЗАДАНИЕ:

#### Тестовое задание 2:

13.Реализовать считывание списка товаров из внешнего файла и сохранение данных чека в указанный CSV-файл на вход
добавлены параметры pathToFile=xxxx и saveToFile=xxxx
* pathToFile - включает относительный (от корневой директории проекта) путь + название файла с расширением (всегда
присутствует в заданном формате)
* saveToFile - включает относительный (от корневой директории проекта) путь + название файла с расширением
* ВАЖНО! если не передан аргумент - pathToFile- ошибку сохранить в result.csv (если передан saveToFile, тогда сохранить 
по пути из saveToFile)
* ВАЖНО! если не передан аргумент - saveToFile- ошибку сохранить в result.csv

* Приложение должно запускаться с помощью консольной команды:
* java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java id-quantity discountCard=xxxx
  balanceDebitCard=xxxx pathToFile=XXXX saveToFile=xxxx
* Пример с ошибкой 1: должен создаться файл result.csv в корне проекта с ошибкой BAD REQUEST:
  java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-1 discountCard=1111
  balanceDebitCard=100 pathToFile=./products.csv
* Пример с ошибкой 2: должен создаться файл error_result.csv в корне проекта с ошибкой BAD REQUEST:
  java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-1 discountCard=1111
  balanceDebitCard=12.1 saveToFile=./error_result.csv
</details>

<details>
 <summary><strong>
  Стек
</strong></summary>

#### При разработке были использованы:

* Java 21

</details>

<details>
 <summary><strong>
  Запуск проекта
</strong></summary>

* Скачайте проект с gitHub:
    * https://github.com/Nikolay1992167/ClevertecCheck.git
* Откройте терминал или командную строку и перейдите в директорию вашего проекта
* Соберите jar-файл с помощью комманды сборщика ```javac -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java```
</details>

<details>
 <summary><strong>
  Валидные команды
</strong></summary>

* java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-1 2-2 3-3 4-4 discountCard=1111 balanceDebitCard=100.01 pathToFile=./src/main/resources/products.csv saveToFile=./result.csv
* java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-1 2-2 3-3 4-4 balanceDebitCard=100.01 pathToFile=./src/main/resources/products.csv saveToFile=./result.csv
</details>

<details>
 <summary><strong>
  Команды с ошибками
</strong></summary>

* Не достаточно средств (NOT ENOUGH MONEY):
*  java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-1 2-2 3-3 18-20 discountCard=2222 balanceDebitCard=5.55 pathToFile=./src/main/resources/products.csv saveToFile=./error_result.csv

* Не передан баланс карты (BAD REQUEST):
* java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-1 2-2 3-3 18-20 discountCard=2222 pathToFile=./src/main/resources/products.csv saveToFile=./error_result.csv

* Не передано ни одного продукта (BAD REQUEST):
* java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java discountCard=2222 balanceDebitCard=50.55 pathToFile=./src/main/resources/products.csv saveToFile=./error_result.csv

* Не передан аргумент - pathToFile:
* java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-1 2-2 3-3 18-20 discountCard=2222 balanceDebitCard=50.55 saveToFile=./result.csv

* Не передан аргумент - saveToFile:
* java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-1 2-2 3-3 18-20 discountCard=2222 balanceDebitCard=205.55 pathToFile=./src/main/resources/products.csv

</details>
