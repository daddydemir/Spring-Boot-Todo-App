package com.example.todomvc.dao;

import com.example.todomvc.entity.User;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@AllArgsConstructor
public class UserDaoImpl implements UserDao{

    private final EntityManager entityManager;

    private Session currentSession(){
        return entityManager.unwrap(Session.class);
    }

    @Override
    public User findUserByEmail(String email) {
        Query<User> query = currentSession().createQuery("from User where email=:email", User.class);
        query.setParameter("email",email);
        return query.getSingleResult();
    }

    @Override
    public void saveUser(User user) {
        currentSession().saveOrUpdate(user);
    }
}
