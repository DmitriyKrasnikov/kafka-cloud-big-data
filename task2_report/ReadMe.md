1. Создана дирректория /opt/nifi/nifi-current/data и  файл для отправки командой внутри контейнера:
   nifi@nifi:/opt/nifi/nifi-current$ cd /opt/nifi/nifi-current/data
   nifi@nifi:/opt/nifi/nifi-current/data$
   cat > input.csv << 'EOF'
   id,name,age,email
   1,Екатерина,21,kate@yandex.ru
   2,Никита,26,nikita@yandex.ru
   3,Майя,21,maya@yandex.ru
   4,Алексей,26,alex@yandex.ru
   5,Илья,25,ilya@yandex.ru
   6,Виктория,22,vika@yandex.ru
   EOF

2. Создан GetFile со следующими настройками:
- ![Снимок экрана кластера](/task2_report/Снимок%20экрана%20(12).png)
2. Создан PublishKafkaRecord_2_0, настроенно SASL_SLL подключение, указан пользователь, пароль, соединен с GetFile:
- ![Снимок экрана кластера](/task2_report/Снимок%20экрана%20(5).png)
2. Создан StandardSSLContextService
- ![Снимок экрана кластера](/task2_report/Снимок%20экрана%20(61).png)
3. Указаны пути к truststore, пароль, формат. Запущен
- ![Снимок экрана кластера](/task2_report/Снимок%20экрана%20(71).png)
- ![Снимок экрана кластера](/task2_report/Снимок%20экрана%20(81).png)
4. Логи работающего nifi
- ![Снимок экрана кластера](/task2_report/Снимок%20экрана%20(9).png)
5. Содержимое топика nifi после запуска PublishKafkaRecord_2_0
- ![Снимок экрана кластера](/task2_report/Снимок%20экрана%20(10).png)
- ![Снимок экрана кластера](/task2_report/Снимок%20экрана%20(11).png)
- ![Снимок экрана кластера](/task2_report/Снимок%20экрана%20(12).png)
6. Логи консьюмера
- [логи консьюмера](../task2_report/nifi_consumer_log.txt)