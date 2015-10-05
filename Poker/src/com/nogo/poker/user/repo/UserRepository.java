package com.nogo.poker.user.repo;

import com.nogo.poker.user.dao.entity.UserEntity;
import com.nogo.poker.user.domain.User;
import com.nogo.poker.user.repo.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepository {

  @Autowired
  private UserDao userDao;

  @Transactional(transactionManager = "databaseTransactionManager")
  public String createUser(final User user) {
    final UserEntity userEntity = new UserEntity();
    userEntity.setId(new Long(1));
    userEntity.setName("Chad");
    userDao.save(userEntity);
    return "1";
  }

  @Transactional(transactionManager = "databaseTransactionManager")
  public User getUser(final Long id) {
    final UserEntity userEntity = userDao.get(id);
    return new User.Builder().withName(userEntity.getName()).build();
  }

}
