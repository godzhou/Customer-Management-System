package com.px.service;

import com.px.dto.PageBean;
import com.px.entity.Customer;

public interface CustomerService {
    PageBean<Customer> findPage(int pagecode,int pagerecords);

    Customer find(String id);

    void addCustomer(Customer customer);

    void update(Customer customer);

    void delete(String id);

    PageBean<Customer> searchCustomer(Customer customer,int pc,int pr);
}
