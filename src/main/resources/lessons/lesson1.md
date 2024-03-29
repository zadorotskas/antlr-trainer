# Введение

Файл в формате .g4 содержит грамматику для анализатора. Пример:

```
lexer grammar Example;

Num         :   [0-9]+;
Other       :   ~[0-9]+;
```

В первой строке файла заголовок lexer grammar говорит о том, что это файл грамматики лексического анализатора; после идет название грамматики, которое должно соответствовать имени файла.
В конце обязательно ставится символ точки с запятой. Шаблон лексического анализатора должен начинаться с заглавной буквы; дальше ставится символ двоеточия, после чего идет само определение шаблона; в конце ставится символ точки с запятой. В примере приведены шаблоны: Num и Other.

Возможные инструменты для задания лексического разбора (регулярные выражения): 
* ’literal’ - Выявление последовательности символов.                                                                                                                                                                                                                                                                                                      
* m* - Ноль или больше совпадений.                                                                                                                                                                                                                                                                                                                 
* m+ - Одно или больше совпадений.                                                                                                                                                                                                                                                                                                                 
* ~m - Совпадение с любым символом не входящим в набор символов описанный в m. Задать набор можно одним символьным литералом, диапазоном или набором подправил.                                                                                                                                                                                    
* m? - Выражению соответствует 0 или 1 экземпляр этого выражения, выражение является необязательным. Например, необязательное «a» перед «b», можно задать как: Exp : a?b                                                                                                                                                                           
* (…) - Сгруппированные последовательности. Например (1|0)+                                                                                                                                                                                                                                                                                            
* [a-b] - Совпадение любого символа в диапазоне от a до b включительно.                                                                                                                                                                                                                                                                               
* . - Соответствие любому одному символу.                                                                                                                                                                                                                                                                                                         
* {«action»} - Выполнить действие в правилах лексического анализатора можно в любом месте. Чтобы выполнить действие для правила, имеющего несколько альтернатив, необходимо альтернативу заключить в круглые скобки. Действие соответствует синтаксису целевого языка. Лексический анализатор копирует содержимое действия в сгенерированный код дословно. 

# Задание
Написать лексический анализатор для распознавания чисел и идентификаторов.
Файл с грамматикой для лексера содержит 3 токена: Num, Indif, Other.
* Num - положительное число.
* Indif - идентификатор, включающий латинские буквы без цифр.
* Other - остальные символы.

Анализатор должен обрабатывать входной поток построчно и выводить результат в формате: \
`Token name: %s, text: %s, length: %s\n`

Осуществлять ввод из консоли в Java можно следующим образом:

```
BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
String str = "";
while (true) {
    str = bf.readLine();
}    
```

Для обработки входных данных лексером необходимо создать его класс:

```
lexer = new Example(CharStreams.fromString(str));
```
В этом примере класс Example - лексер, сгенерированный на основе файла с его грамматикой.

Для получения токенов в созданном лексере есть метод `nextToken()`. Пример использования:
```
Token token = lexer.nextToken();
```

Получить имя токена можно следующим образом: 
```
lexer.getRuleNames()[token.getType() - 1]
```

Получение содержимого токена:
```
token.getText()
```