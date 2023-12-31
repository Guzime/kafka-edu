# kafka-edu



## Кафка - обучение

Репозиторий создан для закрепления основных знаний по кафке:
1. Настройка конфигов подключения приложения к кафке.
2. Умение работать локально работать с кафкой и реестром схем.
3. Создание и настройка различных консюмеров/продюсеров в одном приложении.
4. Работа с авро и реестром схем.
5. Написание интеграционных тестов.

## Задания:

1. Развернуть в докере кафку вместе с реестром схем. Подключиться к ней, используя `Offset Explorer`, проверить, что появился топик с миенем **_schemas**. Добавить, получившийся файл `kafka-edu-compose.yml` в пакет `docker`.
2. Реализовать листенер, который будет получать сообщение из топика `paperless.kafka.edu` (здесь и везде далее, все сообщения будем класть в value, _без использования key_) и десериализует его. Сообщение выглядеть так: 

```json
{
  "instanceId": "string",
  "siebelId": "string",
  "task": {
    "id": "string",
    "requestId": "string",
    "type": "string",
    "status": "string",
    "lifeTime": "string",
    "description": "string"
  },
  "documents": [
    {
      "id": "string",
      "documentType": "string"
    },
    {
      "id": "string",
      "documentType": "string"
    }
  ]
}
```
3. Реализовать продюсер, который кладет в топик `paperless.kafka.edu` сообщение такого же формата. Инициировать отправку сообщения в топик будет контроллер с ручкой `POST http://localhost:8080/kafka-edu/produce`. Продюсера нужно реализовать 2: один будет сериализовать значение (value) как `String` другой же будет использовать твой кастомный pojo.
