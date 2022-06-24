package com.microservices.ch2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class FirstController {
    @Qualifier("ExampleServiceImpl2")
    @Autowired
    lateinit var exampleService: ExampleService

    @RequestMapping(value = ["hello/{name}"], method = [RequestMethod.GET])
    @ResponseBody
    fun hello(@PathVariable name: String) = exampleService.getHello(name)

    @GetMapping("/test/annotation/value")
    @ResponseBody
    fun testAnnotationValue() = exampleService.valueTest()
}