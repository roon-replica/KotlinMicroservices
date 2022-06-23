package com.microservices.ch2

import org.springframework.stereotype.Service

@Service
class ExampleServiceImpl : ExampleService {
    override fun getHello(name: String) = "hello $name"
}