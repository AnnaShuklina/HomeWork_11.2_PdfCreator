Задание:
Написать консольное приложение, которое на выходе создает PDF файл с данными случайных людей.


Данные, которые необходимо сгенерировать:
1. Персональные данные - имя, фамилия, отчество, возраст, пол (М или Ж), дата рождения, место рождения (город);
2. Место проживания - шестизначный почтовый индекс, страна, область, город, улица, дом, квартира.

Требования:
1) В файле один лист с таблицей, в которой сгенерированы данные для n человек, где n - целое число, задается пользователем параметром командной строки или вводом, 1 <= n <= 30;
2) Все текстовые данные на русском языке;
3) Все имена, фамилии и другие значения должны быть адекватными, случайные наборы символов не допускаются;
4) Дату в файл записывать в формате "ДД-ММ-ГГГГ"
5) Имена, фамилии и отчества должны сочетаться с полом, например, женские имена с мужским отчеством не допускаются, как и мужские имена с женским полом;
6) Дата рождения и возраст тоже должны соответствовать друг другу;
7) После того, как файл создан, в лог должно быть выведено сообщение:
   "Файл создан. Путь: *здесь выводим полный путь к файлу*".

Допустимые языки:
Java, Kotlin, Swift, C++

**Результат**:
1) Консольное приложение, которое на вход принимает стандартный ввод. Добавлена обработка ошибок ввода.
2) Часть данных считывается из приложенных текстовых файлов, числа генерируются рандомно в указанных диапазонах. 
Страна у всех персон - Россия 
3) Генерируется файл PeopleDataSet.pdf в папку проекта, где лежат текстовые файлы с данными, выводится полный путь.