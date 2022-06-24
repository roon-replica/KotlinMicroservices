package com.microservices.ch3

import com.microservices.ch3.exception.CustomerNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
class CustomerController {
    @Autowired
    lateinit var customerService: CustomerService

    @GetMapping(value = ["/customer/{id}"])
    fun getCustomer(@PathVariable id: Int): ResponseEntity<Customer> {
//        val customer = customerService.getCustomer(id) ?: throw CustomerNotFoundException("customer $id not found")
//        return ResponseEntity
//            .status(HttpStatus.OK)
//            .body(customer);

        // Exception 던지면 goto처럼 흐름 제어가 안되는? 문제가 있다고 함..
        // 그래서 예외를 안 던질 수 있으면 안 던지는게 낫다고 함
        val customer = customerService.getCustomer(id)
//        return if (customer == null) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
//        else ResponseEntity.status(HttpStatus.OK).body(customer)

        // 이렇게 무조건 ok하고 body에 실질적인 결과를 응답하는 것도 실용적인듯
        return if (customer == null) ResponseEntity.ok(Customer(-1, "unknown"));
        else ResponseEntity.ok().body(customer)
    }

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
    fun createCustomer(@RequestBody customer: ResponseEntity<Customer>): ResponseEntity<Unit> { // Unit = void
        return ResponseEntity
            .created(URI.create("/customer/" + customer.body?.id))  // "?." 라는 safe call이 뭐지
            .build()
    }

    @DeleteMapping("/customer/{id}")
    fun deleteCustomer(@PathVariable id: Int): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (customerService.getCustomer(id) != null) {
            customerService.deleteCustomer(id)
            status = HttpStatus.OK
        }

        return ResponseEntity.status(status).build()
    }

    @PutMapping("/customer/{id}")
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: Customer): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (customerService.getCustomer(id) != null) {
            customerService.updateCustomer(id, customer)
            status = HttpStatus.OK
        }
        return ResponseEntity.status(status).build()
    }

    @GetMapping("/test/exception")
    fun throwException() {
        throw Exception("exception is thrown")
    }
}