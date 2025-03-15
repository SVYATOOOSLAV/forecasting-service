Последовательность действий для запуска приложения
1. На компьютере установлен [Intellij Idea Community Edition](https://www.jetbrains.com/ru-ru/idea/download/other.html)
2. После установки переходим File -> Project Structure -> Project -> SDK -> Download -> version: 17
3. Получаем токен доступа [Токены T‑Bank Invest API](https://www.tbank.ru/invest/settings/) (необходимо быть клиентом Т-Инвестиций)
4. Внутри проекта заходим в [application.yml файл](src/main/resources/application.yaml) и вместо `${CLIENT_INVEST_TOKEN}` помещаем полученный токен
5. Переходим в класс [ForecastingServiceApplication](src/main/java/by/svyat/forecasting/ForecastingServiceApplication.java) и нажимаем зеленую кнопку запуска
6. UI будет доступен [по ссылке](http://localhost:8080/swagger-ui/index.html)


[Презентация к проекту](src/main/resources/Презентация%20для%20конференции.pptx)