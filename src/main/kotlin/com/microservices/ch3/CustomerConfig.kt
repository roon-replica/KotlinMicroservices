package com.microservices.ch3

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.ConcurrentHashMap

@Configuration
class CustomerConfig {
    // TODO : companion object 처음 봄
    // https://kotlinlang.org/docs/object-declarations.html#companion-objects
    companion object {
        val initialCustomers = arrayOf(
            Customer(1, "leemr"),
            Customer(2, "park"),
            Customer(3, "kim")
        )
    }

    // TODO : ConcurrentHashMap 써야하나?
    // read만 하면 안 써도 되겠지
    @Bean
    fun customers() = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))
}