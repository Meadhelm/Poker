package com.nogo.poker.user.dao;

import com.nogo.poker.dao.entity.ResourceEntity;
import com.nogo.poker.user.dao.entity.UserEntity;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

  @Autowired
  private SessionFactory sessionFactory;

  public void save(final ResourceEntity user) {
    sessionFactory.getCurrentSession().save(user);
  }

  public UserEntity get(final String id) {
    return (UserEntity) sessionFactory.getCurrentSession().createCriteria(UserEntity.class)
        .add(Restrictions.eq("id", id)).list().get(0);
  }

}
