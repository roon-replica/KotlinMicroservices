package com.microservices.ch3

interface CustomerService {
    fun getCustomer(id: Int): Customer?
    fun searchCustomers(name: String): List<Customer>
    fun createCustomer(customer: Customer)
    fun updateCustomer(id: Int, customer: Customer)
    fun deleteCustomer(id: Int)

    fun getCustomersValue(): List<Customer>
}