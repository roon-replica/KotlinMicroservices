package com.microservices.ch3

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JsonExampleController {
    @GetMapping("/sample/json")
    fun getJson() = object {
        public val key = "sample key"
        private val value = "sample value"  // 이 값은 private라서 Jackson이 representation으로 바꿔주지 않는다(직렬화 하지 않는다)!!!
        public fun getValue() = value       // 근데 이렇게 getter를 만들어주면 ObjectMapper가 private 속성도 직렬화 해준다
    }

    data class SimpleObject(var key: String = "default key", var value: String = "default value")

    @GetMapping("/sample/json/dataclass")
    fun getJsonDataClass() = SimpleObject("hello", "world")

    data class ComplexObject(var object1: SimpleObject? = null)

    @GetMapping("/sample/json/complex")
    fun getJsonComplexObject() = ComplexObject(object1 = SimpleObject("one depth", "complex"))

    data class MoreComplexObject(var object1: ComplexObject)

    @GetMapping("/sample/json/complexMore")
    fun getJsonMoreComplexObject() = MoreComplexObject(object1 = ComplexObject(SimpleObject("two depth", "complex")))   // 이거 키값 "object1" 말고 다르게 못하나?

}