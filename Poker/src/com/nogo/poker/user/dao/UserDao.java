package com.nogo.poker.user.dao;

import com.nogo.poker.user.dao.entity.UserEntity;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {

  @Autowired
  private SessionFactory sessionFactory;

  public void save(final Object object) {
    sessionFactory.getCurrentSession().save(object);
  }

  public void update(final Object object) {
    sessionFactory.getCurrentSession().update(object);
  }

  /**
   * Returns database resources found by entity example.
   *
   * @param entity example
   * @return findings
   */
  @SuppressWarnings("unchecked")
  public <T extends UserEntity> List<T> findByExample(final T entity) {
    final Example example = Example.create(entity);
    return sessionFactory.getCurrentSession().createCriteria(entity.getClass()).add(example)
        .setFirstResult(0).setMaxResults(10).list();
  }

  /**
   * Returns a single database resource found by entity example.
   *
   * @param entity example
   * @return findings
   */
  @SuppressWarnings("unchecked")
  public <T extends UserEntity> T findById(final String id) {
    return (T) sessionFactory.getCurrentSession().createCriteria(UserEntity.class)
        .add(Restrictions.eq("id", id)).uniqueResult();
  }

}
