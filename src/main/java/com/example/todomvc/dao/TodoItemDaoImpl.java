package com.example.todomvc.dao;

import com.example.todomvc.entity.TodoItem;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;
@Repository
@AllArgsConstructor
public class TodoItemDaoImpl implements TodoItemDao{

    private final EntityManager entityManager;

    private Session currentSession(){
        return entityManager.unwrap(Session.class);
    }

    @Override
    public Collection<TodoItem> getTodosForLoggedUserById(int userId) {
        Query<TodoItem> query = currentSession().createQuery("from TodoItem where userId=:userId", TodoItem.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public void completeTodo(TodoItem item) {
        item.setCompleted(true);
        currentSession().saveOrUpdate(item);
    }

    @Override
    public void deleteTodo(TodoItem item) {
        currentSession().delete(item);
    }
}
