Шаг 1. Был развёрнут кафка кластер с тремя брокерами в Yandex Cloud со следующими аппаратными ресурсами:

![Снимок экрана кластера](/task1_report/Снимок%20экрана%20(320).png)
![Снимок экрана кластера](/task1_report/Снимок%20экрана%20(324).png)
![Снимок экрана кластера](/task1_report/Снимок%20экрана%20(389).png)
- ![Снимок экрана кластера](/task1_report/Снимок%20экрана%20(390).png)

Шаг 2. Была настроена репликация и хранение данных:
- Создан топик с 3 партициями и коэффициентом репликации 3.
- Настроена политика очистки логов (log.cleanup.policy)/
- Установлены параметры хранения (log.retention.ms, log.segment.bytes).
![Снимок экрана кластера](/task1_report/Снимок%20экрана%20(391).png)

Шаг 3. Настроена Schema Registry:
- Развернута Schema Registry, настройки можно посмотреть в [docker-compose.yaml](../docker-compose.yaml)
- Создан пользователь my-schema-registry для настройки sasl подключения
- Скачан сертификат [сертификат](../certs/yandex-ca.pem) и добавлен в [truststore](../certs/yandex-truststore.jks) для для подключения по ssl
- Схема была зарегестрирована при отправке сообщения продюсером

Шаг 4. Проверена работа Kafka:
- Написан [продюсер](../avro-producer) и [консьюмер](../avro-consumer).
- Отправлены тестовые сообщения. Логи [продюсер](../task1_report/producer_log.txt) и [консьюмер](../task1_report/consumer_log.txt).
- Скриншоты логов: [продюсер](../task1_report/producer_log.png) и [консьюмер](../task1_report/consumer_log.png)

Аналог kafka-topics.sh --describe :
![Снимок экрана кластера](../task1_report/Снимок%20экрана%20(16).png)

Скриншот ответа вызова curl http://localhost:8081/subjects
и curl -X GET http://localhost:8081/subjects/<название_схемы>/versions.
![Снимок экрана кластера](../task1_report/Снимок%20экрана%20(17).png)