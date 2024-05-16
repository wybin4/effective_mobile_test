# Тестовое задание Effective Mobile

Стек технологий:
- Kotlin
- Retrofit - библиотека по работе с http. Обязательна к использованию. 
- Корутины, ﻿Flow, LiveData
- Koin
﻿﻿- MVVM
- Верстка на XML
- Clean Architecture без domain-слоя
- Многомодульность

Сделала как смогла и остались вопросы:
- Не поняла как отслеживать завершение ввода в поле места прибытия на странице "Поиск", чтобы перенаправлять на "Поиск по стране". По кнопкам на "Поиск"е понятно, тут бы тоже кнопку. Или же поиск запросами к API - "популярные направления" же там к этому, только без полноценной функциональности, насколько я могу понять
- Как реагировать на состояния загрузки/ошибки/пустого массива при запросах к сети. Никак это не обрабатываю, но состояния есть. Показываю Recycler View при успехе.
- Светлая/темная тема? Или все приложение по дефолту в темной теме?
- ﻿﻿AdapterDelegates не нашла где использовать. Единственное применение вижу на странице "Посмотреть все билеты", чтобы совместить шапку и Recycler View, но там сделала Concat Adapter. Мне так удобнее)
- Нужна ли страница "Фильтры"? В ТЗ пояснений нет вообще.
- Не очень поняла как должна выглядеть заглушка на странице "Поиск", поэтому сделала просто кнопку с текстом.

Скриншоты работы приложения:
<img width="300px" src="https://github.com/wybin4/effective_mobile_test/blob/assets/1.png"/>
<img width="300px" src="https://github.com/wybin4/effective_mobile_test/blob/assets/2.png"/>
<img width="300px" src="https://github.com/wybin4/effective_mobile_test/blob/assets/2_stub.png"/>
<img width="300px" src="https://github.com/wybin4/effective_mobile_test/blob/assets/3.png"/>
<img width="300px" src="https://github.com/wybin4/effective_mobile_test/blob/assets/4.png"/>
