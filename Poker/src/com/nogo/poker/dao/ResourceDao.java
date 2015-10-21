package com.nogo.poker.dao;

import static org.hibernate.criterion.Restrictions.eq;

import com.nogo.poker.domain.Pagination;
import com.nogo.poker.domain.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ResourceDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Transactional(transactionManager = "databaseTransactionManager")
  public String save(final Resource resource) {
    sessionFactory.getCurrentSession()
        .save(resource);
    return resource.getId();
  }

  @Transactional(transactionManager = "databaseTransactionManager")
  public String update(final Resource resource) {
    sessionFactory.getCurrentSession()
        .update(resource);
    return resource.getId();
  }

  /**
   * Returns a single database resource found by id and date.
   *
   * @param id resource id
   * @return findings
   */
  @SuppressWarnings("unchecked")
  @Transactional(transactionManager = "databaseTransactionManager")
  public <T extends Resource> T findById(final String id) {
    return (T) sessionFactory.getCurrentSession()
        .createCriteria(Resource.class)
        .add(eq("id", id))
        .uniqueResult();
  }

  /**
   * something or other.
   *
   * @param pagination woot.
   * @return omg.
   */
  @SuppressWarnings("unchecked")
  @Transactional(transactionManager = "databaseTransactionManager")
  public <T extends Resource> List<T> findAll(final Pagination pagination) {
    return sessionFactory.getCurrentSession()
        .createCriteria(Resource.class)
        .setMaxResults(pagination.getStop())
        .setFirstResult(pagination.getStart())
        .list();
  }

}
