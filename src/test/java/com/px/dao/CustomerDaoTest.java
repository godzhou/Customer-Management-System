package com.px.dao;

import com.px.dto.PageBean;
import com.px.entity.Customer;
import com.px.service.CustomerService;
import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public class CustomerDaoTest extends TestCase{

    private static final Logger logger = Logger.getLogger(CustomerDaoTest.class);

    @Autowired
    public CustomerDao customerDao;

    @Autowired
    private CustomerService customerService;


    @Test
    public void setData(){
        for (int i = 0;i < 100; i++){
            Customer customer = new Customer();
            customer.setId("2015220204" + i);
            customer.setName("customer_test" + i);
            customer.setGender(i % 2 == 0 ? "male" : "female");
            customer.setPhone("13485623" + i);
            customer.setEmail("customer_test" + i + "@163.com");
            customer.setDescription("hello world!");

            logger.debug("----" + customer.toString());
            customerDao.add(customer);
        }
    }

    @Test
    public void test(){
        logger.debug("--------------before dao-----------------------------------");
        PageBean<Customer> pb = customerDao.findAll(3,10);
        logger.debug("--------------------------------------here after dao----------------------------------------------");
        logger.debug("----------info--------->" + pb.getBeanList().size());
        for (Customer customer : pb.getBeanList()){
            logger.debug("---------------------------" + customer.getId());
        }
    }

    @Test
    public void editTest(){
        logger.info("-------------------begin edit-------------------------");
        Customer customer = customerDao.find("ced8d0e89c8145cf9901f6dbf998db95");
        logger.info("--------------name----------------" + customer.getName());
    }

    @Test
    public void delete_test(){
        String id = "1024";
        customerDao.delete(id);
    }

    @Test
    public void aopTest(){
        String id = "1026";
        Customer customer = customerService.find(id);
        logger.info("return value: ----->" + customer.toString());
    }
}
