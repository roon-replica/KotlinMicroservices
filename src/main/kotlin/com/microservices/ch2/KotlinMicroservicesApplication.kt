package com.microservices.ch2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@SpringBootApplication
class KotlinMicroservicesApplication

@Controller
class FirstController {
    @Autowired
    lateinit var exampleService: ExampleService

    @RequestMapping(value = ["hello/{name}"], method = [RequestMethod.GET])
    @ResponseBody
    fun hello(@PathVariable name: String) = exampleService.getHello(name)
}

fun main(args: Array<String>) {
    runApplication<KotlinMicroservicesApplication>(*args)
}
