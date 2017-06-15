# SUBDProject
Project for SUBD class in TUES


## Idea
Приложението представлява чат, осъществен в терминала.
Може да се създават user-и. Те пишат съобщения публично. 
<br>Съобщенията са кодирани и се смята, че само user-а към 
който са насочени, може да прочете(имат и тип - примерно морзов код).
<br>Но все пак съобщенията не са нерешими, и ако някой user
проявява интерес може да предложи възможно решение.
Решението включва user-а, написал го, съобщението, което 
се превежда, и самия декриптиран текст.

## CodedChat Database Structure
- Table: **Users**
  + Id
  + Name
  <br>
- Table: **MessageTypes**
  + Id
  + Name
  + `[CreatorUserId]`
  <br>
- Table: **Messages**
  - Id
  - Text
  - [UploadedDate]
  - `ToUserId`
  - `TypeId`
  <br>
- Table: **Solutions**
  - Id
  - DecodedText
  - `UserId`
  - `MessageId`
