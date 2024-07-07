<h1 align="center">Консольное приложение, реализующее функционал формирования чека в магазине</h1>

<details>
 <summary><strong>
  Техническое задание
</strong></summary>

#### ЗАДАНИЕ:

#### Тестовое задание 1:

Приложение должно запускаться с помощью консольной команды:
java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java id-quantity discountCard=xxxx
balanceDebitCard=xxxx
где:
id - идентификатор товара (см. Таблицу 1)
quantity - количество товара
discountCard=xxxx - название и номер дисконтной карты (см. Таблицу 2)
balanceDebitCard=xxxx - баланс на дебетовой карте
Примечание:
● Название и путь CSV-файла: result.csv в корне проекта
● всё указываем через пробел
● id-quantity - добавляем префикс id-(количество товара). В наборе параметров
должна быть минимум одна такая связка "id-quantity"
● discountCard=xxxx - добавляем префикс discountCard=(любые четыре цифры)
● balanceDebitCard=xxxx - указываем префикс balanceDebitCard=(любая сумма
на счете). Обязательно должна присутствовать. Баланс может быть как и с
двумя знаками после запятой, так и отрицательный
ВАЖНО! id могут повторяться: 1-3 2-5 1-1 тоже, что и 1-4 2-5
ВАЖНО! xxxx - для числа с плавающей точкой разделитель точка
Пример: 1.12

Пример:
java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 3-1 2-5 5-1 discountCard=1111
balanceDebitCard=100
По команде выше должен сформироваться CSV-файл (result.csv) в корне проекта, содержащий в себе список товаров и их
количество с ценой, а также рассчитанную сумму с учетом скидки по предъявленной карте, если она есть.
Расшифровка команды: (3-1) 3 - это товар с id = 3, 1 - количество (1шт);
тоже самое с (2-5) id=2 в количестве 5 штук, (5-1) id=5 - одна штука и т. д.;
discountCard=1111 - означает, что была предъявлена скидочная карта с номером 1111. Необходимо продублировать чек в
консоль.
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
    * https://github.com/NortinPowers/vadarod-test-task.git
* Откройте терминал или командную строку и перейдите в директорию вашего проекта
* Соберите jar-файл с помощью комманды сборщика ```javac -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java```

</details>

<details>
 <summary><strong>
  Валидные команды
</strong></summary>

      ``` 
      java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-1 1-2 3-5 14-10 discountCard=1111 balanceDebitCard=100.01
      java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 10-7 11-2 discountCard=5555 balanceDebitCard=111
      java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 3-3 2-1 balanceDebitCard=111.11
      ```

</details>

<details>
 <summary><strong>
  Команды с ошибками
</strong></summary>

      ``` 
      java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-1 1-24 10-8 discountCard=1111 balanceDebitCard=50
      java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 1-1 1-4 10-2 discountCard=1111 balanceDebitCard=10
      java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java discountCard=1111 balanceDebitCard=10
      ```

</details>

<details>
 <summary><strong>
  Результат
</strong></summary>

result.csv в корне проекта.

</details>