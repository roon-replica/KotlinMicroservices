package com.microservices.ch2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@SpringBootApplication
class KotlinMicroservicesApplication

@Controller
class FirstController {
    @RequestMapping(value = ["hello"], method = [RequestMethod.GET])
    @ResponseBody
    fun hello() = "this is my first kotlin controller!!!!"
}

fun main(args: Array<String>) {
    runApplication<KotlinMicroservicesApplication>(*args)
}
