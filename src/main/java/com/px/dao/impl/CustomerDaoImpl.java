package com.px.dao.impl;

import com.px.dao.CustomerDao;
import com.px.dto.PageBean;
import com.px.entity.Customer;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao{

    private static final Logger logger = Logger.getLogger(CustomerDaoImpl.class);

    //增
    @Override
    public void add(Customer c) {
        super.add(c);
    }

    @Override
    /**
     * 查
     * @Param pc page code
     * @Param pr page recodes
     */
    public PageBean<Customer> findAll(int pc, int pr) {
        PageBean<Customer> pb = new PageBean<Customer>();
        pb.setPc(pc);
        pb.setPr(pr);

        String hql_count = "select count(*) from Customer";
        Number number = (Number) getCurrentSession().createQuery(hql_count).uniqueResult();

        int tr = number.intValue();
        pb.setTr(tr);
        pb.setTp();

        String hql_all = "select c from Customer as c";
        List<Customer> customerList = getCurrentSession()
                .createQuery(hql_all)
                .setFirstResult((pc-1)*pr).setMaxResults(pr)
                .list();

        pb.setBeanList(customerList);
        return pb;
    }

    //修改了用户信息
    @Override
    public void edit(Customer c) {
        super.update(c);
    }

    //删
    @Override
    public void delete(String id) {
        String hql = "select c from Customer as c where c.id = ?";
        Customer customer = (Customer) getCurrentSession().createQuery(hql).setParameter(0,id).uniqueResult();
        super.delete(customer);
    }

    @Override
    public PageBean<Customer> query(Customer c,int pc ,int pr) {
        PageBean<Customer> pageBean = new PageBean<Customer>();
        pageBean.setPc(pc);
        pageBean.setPr(pr);

        String hqlHead = "select count(*) from Customer as c ";
        StringBuffer countHql = new StringBuffer(" where 1=1 ");
        StringBuffer showHql = new StringBuffer();

        String id = c.getId();
        if (id != null && !id.trim().isEmpty()){
            countHql.append(" and c.id like '%" + id + "%' ");
        }
        String name = c.getName();
        if (name != null && !name.trim().isEmpty()){
            logger.info("----NAME----------------->>" + name);
            countHql.append(" and c.name like '%" + name + "%' ");
        }
        String gender = c.getGender();
        if (gender != null && !gender.trim().isEmpty()){
            countHql.append(" and c.gender = '" + gender +"'");
        }
        String phone = c.getPhone();
        if (phone != null && !phone.trim().isEmpty()){
            countHql.append(" and c.phone like '%" + phone + "%' ");
        }
        String email = c.getEmail();
        if (email != null && !email.trim().isEmpty()){
            countHql.append(" and c.email like '%" + email + "%' ");
        }

        String fullHql = hqlHead + countHql.toString();

        logger.info(" ------ HQL ------ " + fullHql);

        Number number = (Number)getCurrentSession()
                .createQuery(fullHql)
                .uniqueResult();
        int tr = number.intValue();
        pageBean.setTr(tr);
        pageBean.setTp();

        String customerHql = "select c from Customer as c ";
        String selectHql = customerHql + countHql.toString();
        List<Customer> list = getCurrentSession().
                createQuery(selectHql)
                .setFirstResult((pc - 1) * pr).setMaxResults(pr)
                .list();

        pageBean.setBeanList(list);
        return pageBean;
    }

    @Override
    public Customer find(String id) {
        String hql = "select c from Customer as c where c.id = ?";
//        Number number = (Number) getCurrentSession().createQuery(hql).setParameter(0,id).uniqueResult();
        Customer customer = (Customer) getCurrentSession().createQuery(hql).setParameter(0,id).uniqueResult();
        return customer;
    }
}
