## Reproduce the issue

### 2.7

```shell
$ curl http://localhost:8080
someString=null

$ curl "http://localhost:8080?someOptionalObject=foo"
someString=foo
```

### 3.2

```shell
$ curl http://localhost:8080
{"timestamp":"2023-12-12T21:05:04.258+00:00","status":400,"error":"Bad Request","path":"/"}                                                                                                                                                                                                             

$  curl "http://localhost:8080?someOptionalObject=foo"
someString=foo
```