package com.microservices.ch3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ConcurrentHashMap

@RestController
class CustomerController {
    @Autowired
    lateinit var customers: ConcurrentHashMap<Int, Customer>

    @GetMapping(value = ["/customer/{id}"])
    fun getCustomer(@PathVariable id: Int): Customer = customers.getOrDefault(id, Customer(id, "unknown"))

    @GetMapping("/customers")
    fun getCustomersAll() = customers

    @GetMapping("/customers/value")
    fun getCustomersValue() = customers.map(Map.Entry<Int, Customer>::value).toList()

}