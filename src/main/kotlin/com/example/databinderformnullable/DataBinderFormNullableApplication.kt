package com.example.databinderformnullable

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@SpringBootApplication
class DataBinderFormNullableApplication

@Controller
class TestController {
  @GetMapping("/")
  fun test(form: Form): String {
    return "test"
  }
}

class Form(
  val someOptionalObject: SomeOptionalObject? = null
)

class SomeOptionalObject(
  val someString: String
)

fun main(args: Array<String>) {
  runApplication<DataBinderFormNullableApplication>(*args)
}
