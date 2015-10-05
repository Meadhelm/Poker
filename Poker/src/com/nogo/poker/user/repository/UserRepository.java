package com.nogo.poker.user.repository;

import static java.util.Collections.unmodifiableCollection;

import com.nogo.poker.user.dao.ResourceDao;
import com.nogo.poker.user.dao.UserDao;
import com.nogo.poker.user.dao.entity.UserEntity;
import com.nogo.poker.user.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public class UserRepository {

  @Autowired
  private ResourceDao dao;

  @Autowired
  private UserDao userDao;

  /**
   * Creates a new user resource.
   *
   * @param user The user to create.
   * @return The resource id.
   */
  @Transactional(transactionManager = "databaseTransactionManager")
  public String createUser(final User user) {
    final UserEntity entity = user.toEntity();
    dao.save(entity);
    return entity.getId();
  }

  @Transactional(transactionManager = "databaseTransactionManager")
  public User getUser(final String id) {
    return userDao.get(id).toDomain();
  }

  /**
   * Returns user records matching input parameters.
   *
   * @param firstName the first name to match on
   * @return a collection of user records
   */
  @Transactional(transactionManager = "databaseTransactionManager")
  public Collection<User> findByExample(final String firstName) {
    final UserEntity example = new UserEntity();
    example.setFirstName(firstName);

    final Collection<User> users = new ArrayList<>();
    for (final UserEntity entity : dao.findByExample(example)) {
      users.add(entity.toDomain());
    }
    return unmodifiableCollection(users);
  }

}
