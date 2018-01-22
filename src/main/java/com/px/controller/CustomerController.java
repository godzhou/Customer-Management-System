package com.px.controller;

import com.px.dao.impl.CustomerDaoImpl;
import com.px.dto.PageBean;
import com.px.entity.Customer;
import com.px.service.CustomerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class CustomerController {

    private static final Logger logger = Logger.getLogger(CustomerController.class);

    private int pr = 10;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/edit/{customerid}")
    public String edit(HttpServletRequest request,@PathVariable String customerid){
        logger.debug("----------------id----------------------------" + customerid);
        Customer customer = customerService.find(customerid);
        if (customer == null){
            request.setAttribute("msg","没有找到相关用户！");
            return "msg";
        }
        else{
            request.setAttribute("customer",customer);
            return "/edit";
        }
    }
    @RequestMapping(value = "/delete/{customerid}")
    public String delete(HttpServletRequest request,@PathVariable String customerid){
        customerService.delete(customerid);

        request.setAttribute("msg","删除用户成功！");
        return "msg";
    }

    @RequestMapping(value = "/update")
    public String update(HttpServletRequest request,Customer customer){
        customerService.update(customer);
        request.setAttribute("msg","编辑客户信息成功！");
        return "msg";
    }

    @RequestMapping(value = "/findAll")
    public String findAll(HttpServletRequest request, Integer pc) {
//        int pc = getPc(request);
        if (pc == null) {
            pc = 1;
        }

        PageBean<Customer> pageBean = customerService.findPage(pc, pr);
        request.setAttribute("pb", pageBean);
        return "list";
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public String addCustomer(Customer customer) {
        if (customer.getId() == null){
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            customer.setId(uuid);
        }
        customerService.addCustomer(customer);
        return "add";
    }

    @RequestMapping(value = "/query")
    public String query(HttpServletRequest request,Customer customer,Integer pc){

        if (pc == null){
            pc = 1;
        }

        logger.info("-------------------------------name in controller--------------------------" + customer.getName());

        PageBean<Customer> pb = customerService.searchCustomer(customer,pc,pr);
        if (pb == null || (pb.getBeanList().size() == 0)){
            request.setAttribute("msg","没有找到相关用户！");
            return "msg";
        }
        else{
            request.setAttribute("pb",pb);
            return "querylist";
        }

    }


}
