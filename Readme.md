
# Project Title

this is a user-service where users can login, sign-up, search for other users and the admin can look at all users
Used:
Spring Boot
Spring Data Jpa
Spring Security(JWT)
Lombok
Liquibase
Docker
PostgreSQL



## BUILD THE APPLICATION
./gradlew build
## BUILD AND UP Docker Compose
docker-compose up --build
docker-compose down <- down docker compose

## API Reference

#### For Login

```http
  POST localhost:8080/login
```

| Parameter | Value     | password               |roles     |
| :-------- | :------- | :---------------------- |:---------------------- |
| `username` | `gshahrza` | `qweR1234*` |  `ADMIN`            |
| `username` | `smikayilov08` | `qweR1234#` | `USER,MODERATOR`      |
| `username` | `malikk` | `qwEr1234*` |       `USER`       |

`Example for requestBody`:
{
"username" : "gshahrza",
"password" : "qweR1234*"
}
`Response Body`: { "jwt" : "body"}

#### Get all users

```http
  GET localhost:8080/users
```

| Header |  Description          | allowed|
| :-------- | :--------------------- |:------|
| `Authorization`| **Required**. Bearer tokenBody | `Only admin`|

##### Get user by id :
```http
  GET localhost:8080/users/{id}
```
| Parameter | Type     | allowed   |
| :-------- | :------- |:---------- |
| `user id` | `number` |  `All users`   |

##### Get user by username :
```http
  GET localhost:8080/users/?username=value
```
| Parameter | Type     | allowed   |
| :-------- | :------- |:---------- |
| `username` | `String` |  `All users`   |

##### Role add to user :
```http
  POST localhost:8080//roles/add-to-user
```
| Parameter | Type     | allowed   |
| :-------- | :------- |:---------- |
| `username` | `String` |  `Only Admin`   |
| `roleName` | `String` |

|Header | Description          | allowed|
| :----- |:--------------------- |:------|
|`Authorization`|**Required**. Bearer tokenBody | `Only admin`|

`Example : ` `RequestBody : ` {"username" : "value","roleName" : "value"}
##### Role delete from user :
```http
  POST localhost:8080//roles/add-to-user
```
| Parameter | Type     | allowed   |
| :-------- | :------- |:---------- |
| `username` | `String` |  `Only Admin`   |
| `roleName` | `String` |

|Header | Description          | allowed|
| :----- |:--------------------- |:------|
|`Authorization`|**Required**. Bearer tokenBody | `Only admin`|

`Example : ` `RequestBody : ` {"username" : "value","roleName" : "value"}

