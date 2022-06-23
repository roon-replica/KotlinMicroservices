package com.microservices.ch2

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.stereotype.Service

@Service("ExampleServiceImpl2")
class ExampleServiceImpl2 : ExampleService {
    @Value(value = "\${service.message.hello2}")
    private lateinit var hello: String

    override fun getHello(name: String) = "$hello $name"

    override fun valueTest() {}
}