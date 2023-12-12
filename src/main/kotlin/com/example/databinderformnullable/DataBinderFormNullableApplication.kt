package com.example.databinderformnullable

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.convert.TypeDescriptor
import org.springframework.core.convert.converter.ConditionalGenericConverter
import org.springframework.core.convert.converter.GenericConverter
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class DataBinderFormNullableApplication

@RestController
class TestController {
  @GetMapping("/")
  fun test(form: Form): String {
    return "someString=${form.someOptionalObject?.someString}"
  }
}

class Form(
  val someOptionalObject: SomeNestedObject? = null
)

// Note: The actual object is more complex than that
class SomeNestedObject(
  val someString: String,
)

// Note: Simplified for this example. The actual converter uses JSON.
@Component
class SomeConverter : ConditionalGenericConverter {
  override fun getConvertibleTypes() = setOf(
    GenericConverter.ConvertiblePair(String::class.java, Any::class.java),
    GenericConverter.ConvertiblePair(Any::class.java, String::class.java)
  )

  override fun matches(sourceType: TypeDescriptor, targetType: TypeDescriptor): Boolean {
    val notStringTypeDescriptor = if (sourceType.type == String::class.java) {
      targetType
    } else {
      sourceType
    }
    // Note: The actual logic here is more complex than that
    return notStringTypeDescriptor.type == SomeNestedObject::class.java
  }

  override fun convert(source: Any?, sourceType: TypeDescriptor, targetType: TypeDescriptor): Any? {
    if (source == null) {
      return null
    }
    return if (sourceType.type == String::class.java) {
      SomeNestedObject(source as String)
    } else {
      (source as SomeNestedObject).someString
    }
  }
}

fun main(args: Array<String>) {
  runApplication<DataBinderFormNullableApplication>(*args)
}
