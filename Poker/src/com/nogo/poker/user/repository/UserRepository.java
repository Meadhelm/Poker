package com.nogo.poker.user.repository;

import static java.util.Collections.unmodifiableCollection;

import com.nogo.poker.user.dao.UserDao;
import com.nogo.poker.user.dao.entity.UserEntity;
import com.nogo.poker.user.domain.User;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public class UserRepository {

  @Autowired
  private UserDao dao;

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

  /**
   * Updates an existing user resource.
   *
   * @param existingUser The user to update.
   * @param newUser The new user to persist.
   * @return The resource id.
   */
  @Transactional(transactionManager = "databaseTransactionManager")
  public String updateUser(final User existingUser, final User newUser) {
    final UserEntity oldEntity = existingUser.toEntity();
    final UserEntity newEntity = newUser.toEntity();
    oldEntity.getTrackable().setEndDate(newUser.getEffectiveDate());
    dao.update(oldEntity);
    dao.save(newEntity);
    return newEntity.getId();
  }

  /**
   * Returns user records matching input parameters.
   *
   * @param example the example object
   * @return a collection of user records
   */
  @Transactional(transactionManager = "databaseTransactionManager")
  public Collection<User> findByExample(final User example) {
    final Collection<User> users = new ArrayList<User>();
    for (final UserEntity userEntity : dao.findByExample(example.toEntity())) {
      users.add(userEntity.toDomain());
    }
    return unmodifiableCollection(users);
  }

  /**
   * Returns user records matching input parameters.
   *
   * @param id the example object
   * @return a collection of user records
   */
  @Transactional(transactionManager = "databaseTransactionManager")
  public User findById(final String id) {
    final UserEntity userEntity = dao.findById(id, DateTime.now());
    if (userEntity == null) {
      return null;
    }
    return userEntity.toDomain();
  }

}
