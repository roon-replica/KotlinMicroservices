package com.microservices.ch3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class CustomerServiceImpl : CustomerService {
    @Autowired
    lateinit var customers: ConcurrentHashMap<Int, Customer>

    override fun getCustomer(id: Int): Customer? = customers.getOrDefault(id, null)

    override fun searchCustomers(nameFilter: String): List<Customer> = customers
        .filter { it.value.name.contains(nameFilter) } // https://kotlinlang.org/docs/lambdas.html#it-implicit-name-of-a-single-parameter
        .map(Map.Entry<Int, Customer>::value)
        .toList()

    override fun getCustomersValue() = customers.map(Map.Entry<Int, Customer>::value).toList()

    override fun createCustomer(customer: Customer) {
        customers[customer.id] = customer
    }

    override fun updateCustomer(id: Int, customer: Customer) {
        customers[id] = customer
    }

    override fun deleteCustomer(id: Int) {
        customers.remove(id)        // remove하면 remove한 객체 리턴하나 보네. 근데 메서드에서 마지막 줄이 자동으로 리턴되는거 아니었나?
    }
}