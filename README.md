## spring-rest
#### REST API app with using Spring MVC, JPA, AOP, MapStruct, Postgres 14

Realize simple News-feed with 4 entites: Users, Categories, News, Comments

Run:
1. docker/docker compose up
2. gradlew bootrun

#### API:

Users:
+ GET /api/user - get all users. Params pageSize, pageNumber required
+ GET /api/user/{id} - get user by id.
+ POST /api/user - create, body: name   
+ PUT /api/user/{id} - update, body: name
+ DELETE /api/user/{id} - delete 

Categories:
+ GET /api/category, params pageSize, pageNumber required
+ GET /api/category/{id}
+ POST /api/category, body: title
+ PUT /api/category/{id}, body: title
+ DELETE /api/category/{d}

News:
+ GET /api/news, params pageSize, pageNumber required. Filter by params userId, categoryId. Return comments count
+ GET /api/news/{id}, return all comments
+ POST /api/news, body: title, text, categoryId, userId (created user)
+ PUT /api/news/{id}, body: title, text, categoryId, userId (user that performing update, must be equal userId in db)
+ DELETE /api/news/{id}, body: userId - id user, that performing delete 

Comments:
+ GET /api/comment/{id}
+ POST /api/comment, body: text, newsId, userId
+ PUT /api/comment/{id}, body: text, newsId, userId (user that performing update, must be equal userId in db)
+ DELETE /api/comment/{id}, body: userId - id user, that performing delete

Edit docker/docker-compose.yml and src/main/resources/application.yml to setting port, and etc 