package com.microservices.ch2

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service("ExampleServiceImpl")
class ExampleServiceImpl : ExampleService {
    // TODO : 역슬래시 왜 필요하지?
    @Value(value = "\${service.message.hello}")
    private lateinit var hello: String

    override fun getHello(name: String) = "$hello $name"

    @Value(value = "#{1+2}")
    private lateinit var number1: Number

    @Value(value = "#{\${one.value} eq \${two.value} }")
    private lateinit var booleanResult: Comparable<Boolean>

    override fun valueTest() {
        println(number1)
        println(booleanResult)
    }
}