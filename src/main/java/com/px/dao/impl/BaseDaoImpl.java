package com.px.dao.impl;

import com.px.dao.BaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Transactional
public class BaseDaoImpl<T> implements BaseDao<T>{

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Serializable save(T o) {
        return this.getCurrentSession().save(o);
    }

    @Override
    public void saveOrUpdate(T o) {
        this.getCurrentSession().saveOrUpdate(o);
    }

    @Override
    public void delete(T o) {
        this.getCurrentSession().delete(o);
    }

    @Override
    public void update(T o) {
        this.getCurrentSession().update(o);
    }

    public void add(T t){
        this.getCurrentSession().save(t);
    }

}
