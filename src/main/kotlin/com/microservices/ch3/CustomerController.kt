package com.microservices.ch3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerController {
    @Autowired
    lateinit var customerService: CustomerService

    @GetMapping(value = ["/customer/{id}"])
    fun getCustomer(@PathVariable id: Int): Customer? = customerService.getCustomer(id)

    @GetMapping("/customers")
    fun searchCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
        customerService.searchCustomers(nameFilter)

    @GetMapping("/customers/value")
    fun getCustomersValue() = customerService.getCustomersValue()

    @GetMapping("/customer/anonymous/expression")
    fun anonymousObjectExpression() = object {
        val id = -1
        val name = "anonymous object expression test"
    }

    @GetMapping("/customer/anonymous/declaration")
    fun anonymousObjectDeclaration() = SampleCustomer

    object SampleCustomer {
        val sample = Customer(-1, "anonymous object declaration test")
    }

    @PostMapping("/customer")
    fun createCustomer(@RequestBody customer: Customer) = customerService.createCustomer(customer)

    @DeleteMapping("/customer/{id}")
    fun deleteCustomer(@PathVariable id: Int) = customerService.deleteCustomer(id)

    @PutMapping("/customer/{id}")
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: Customer) =
        customerService.updateCustomer(id, customer)
}