package com.px.service.impl;

import com.px.dao.CustomerDao;
import com.px.dto.PageBean;
import com.px.entity.Customer;
import com.px.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDao customerDao;

    @Override
    public PageBean<Customer> findPage(int pagecode, int pagerecords) {
        return customerDao.findAll(pagecode,pagerecords);
    }

    @Override
    public Customer find(String id) {
        return customerDao.find(id);
    }

    @Override
    public void delete(String id) {
        customerDao.delete(id);
    }

    @Override
    public void addCustomer(Customer customer) {
        customerDao.add(customer);
    }

    @Override
    public PageBean<Customer> searchCustomer(Customer customer,int pc,int pr) {
        return customerDao.query(customer,pc,pr);
    }

    @Override
    public void update(Customer customer) {
        customerDao.update(customer);
    }
}
