package com.nogo.poker.user.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourceDao {

  @Autowired
  private SessionFactory sessionFactory;

  public void save(final Object object) {
    sessionFactory.getCurrentSession().saveOrUpdate(object);
  }

  public void update(final Object object) {
    sessionFactory.getCurrentSession().saveOrUpdate(object);
  }

  @SuppressWarnings("unchecked")
  public <T> List<T> findByExample(final T entity) {
    final Example example = Example.create(entity);
    return sessionFactory.getCurrentSession().createCriteria(entity.getClass()).add(example).list();
  }

}
