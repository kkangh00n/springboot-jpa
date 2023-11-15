package com.example.springbootjpa.domain.customer;

import com.example.springbootjpa.domain.customer.Customer;
import com.example.springbootjpa.domain.customer.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void test() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("kkangh00n");
        customer.setLastName("kim");

        customerRepository.save(customer);

        Customer entity = customerRepository.findById(1L).get();
        log.info("{} {}", entity.getLastName(), entity.getFirstName());
    }
}