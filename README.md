# 🇺🇸 US Top Headlines - Android News App

This is a simple yet scalable **News Reader App** built in **Java (Android)** using **MVVM architecture**, fetching the latest top headlines from the **United States** via the [NewsAPI](https://newsapi.org/) and displaying additional like/comment data using Condé Nast internal APIs.

---

##  Features

-  Fetches real-time US top headlines
-  Displays title, description, author, and image for each article
-  Placeholder image shown when no image is provided
-  Tapping an article opens a detail screen
-  Detail screen shows number of likes and comments
-  MVVM architecture with clear separation of concerns
-  Optimized using Glide, Retrofit2, ViewModel, and LiveData

---

##  Screenshots

### News List Screen
![News List](screenshots/news_list.png)

### News List with No Image Screen
![News List with no Image](screenshots/no_img_news.png)

### 📖 Detail Screen with Likes & Comments
![Detail Screen](screenshots/detail_screen.png)

---

##  Tech Stack

| Layer         | Tools/Libraries Used                                |
|---------------|-----------------------------------------------------|
| Architecture  | MVVM (Model - ViewModel - Repository)               |
| Network       | Retrofit + GSON + Coroutines                        |
| Image Loading | Glide                                               |
| UI            | RecyclerView, ConstraintLayout                      |
| Language      | Java                                                |
| Others        | LiveData, ViewModel, Intent navigation              |

---

##  APIs Used

1. **NewsAPI** - for fetching top headlines  
   Endpoint: `https://newsapi.org/v2/top-headlines?country=us&apiKey=API_KEY`

2. **Condé Nast Likes & Comments**
    - Likes: `https://cn-news-info-api.herokuapp.com/likes/<ARTICLEID>`
    - Comments: `https://cn-news-info-api.herokuapp.com/comments/<ARTICLEID>`

 *Note:* `<ARTICLEID>` = article URL without scheme (http/https), with `/` replaced by `-`.

Example:  
Original Article URL:  
`https://www.theverge.com/2020/7/21/21332300/nikon-z5-full-frame-mirrorless-camera-price-release-date-specs/index.html`

Converted ARTICLEID:  
`www.theverge.com-2020-7-21-21332300-nikon-z5-full-frame-mirrorless-camera-price-release-date-specs-index.html`

