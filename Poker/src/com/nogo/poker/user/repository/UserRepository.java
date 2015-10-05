package com.nogo.poker.user.repository;

import com.nogo.poker.user.dao.UserDao;
import com.nogo.poker.user.dao.entity.UserEntity;
import com.nogo.poker.user.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepository {

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
    final UserEntity userEntity = new UserEntity(user);
    userDao.save(userEntity);
    return userEntity.getId();
  }

  @Transactional(transactionManager = "databaseTransactionManager")
  public User getUser(final String id) {
    final UserEntity userEntity = userDao.get(id);
    return userEntity.toDomainObject();
  }

}
