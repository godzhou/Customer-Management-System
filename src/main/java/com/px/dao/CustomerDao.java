package com.px.dao;

import com.px.dto.PageBean;
import com.px.entity.Customer;

public interface CustomerDao extends BaseDao<Customer>{

    Customer find(String id);

    public void add(Customer c);

    public PageBean<Customer> findAll(int pc,int pr);

    public void edit(Customer c);

    public void delete(String id);

    public PageBean<Customer> query(Customer c,int pc,int pr);
}
