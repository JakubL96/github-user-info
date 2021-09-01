# githubUserInfo

Collect information about users from github

Server run on port 8080

API:
```
GET /users/{login}
```
Example Request
```
GET /users/octocat
```

Example Response
```
{
  "login": "octocat",
  "id": 583231,
  "name": "The Octocat",
  "type": "User",
  "calculations": 2,
  "avatar_url": "https://avatars.githubusercontent.com/u/583231?v=4",
  "created_at": "2011-01-25T18:44:36.000+00:00"
}
```