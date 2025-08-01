APK доступен в разделе [Releases](https://github.com/HSact/UserFormPurchasesApp/releases)
## О проекте

Тестовое Android-приложение с тремя экранами:

- Главный экран с навигацией  
- Форма регистрации с валидацией (поля не пустые, номер участника — 16 цифр)  
- Экран списка покупок, сгруппированных по дате  

---

## Архитектура и технологии

- Kotlin, Jetpack Compose  
- Hilt для DI  
- Coroutines и Flow для асинхронности и реактивности  
- StateFlow и sealed классы для управления состоянием UI  
- Локальный источник данных — JSON из assets  
- Группировка и сортировка данных реализованы в UseCase  
- Навигация через Jetpack Navigation Compose   

---

## Структура проекта

- `ui.screens` — экраны и ViewModel  
- `domain.usecase` — бизнес-логика, трансформация данных  
- `data.repository` — загрузка и парсинг JSON  
- `model` — модели данных  

---

## Особенности реализации

- UseCase возвращает Flow сгруппированных по дате покупок  
- ViewModel подписывается на Flow, обновляет UiState 
- Валидация формы реализована с обновлением состояния кнопки регистрации  
- Список покупок отображается в LazyColumn, дата — заголовком группы  
- Ошибки загрузки отображаются в UI  
- Код покрыт KDoc  

---

## Скриншоты

<table>
  <tr>
    <td><img src="screenshots/profile.png" alt="Профиль" width="250"/></td>
    <td><img src="screenshots/purchases.png" alt="Список покупок" width="250"/></td>
  </tr>
  <tr>
    <td><img src="screenshots/registration_invalid.png" alt="Регистрация 1" width="250"/></td>
    <td><img src="screenshots/registration_valid.png" alt="Регистрация 2" width="250"/></td>
  </tr>
</table>
