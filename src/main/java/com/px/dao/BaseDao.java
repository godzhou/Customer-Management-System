package com.px.dao;

import java.io.Serializable;

/**
 * @author pengxiang
 */
public interface BaseDao<T> {
    public Serializable save(T o);

    public void saveOrUpdate(T o);

    public void delete(T o);

    public void update(T o);
}
