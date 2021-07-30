package com.example.Boot311.dao;

import com.example.Boot311.models.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void add(User user) {
        em.persist(user);
    }

    @Override
    public void edit(User user) {


        em.merge(user);
    }

    @Override
    public void delete(long id) {
        em.remove(getUserById(id));
    }

    @Override
    public User getUserById(long id) {
        return em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.id=:id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<User> listUsers() {
        return em.createQuery("SELECT DISTINCT  u FROM User u  LEFT JOIN FETCH u.roles", User.class).getResultList();

    }

    @Override
    public UserDetails getUserByName(String s) {
        return em.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=:email ", User.class).
                setParameter("email", s).getSingleResult();
    }
}
//        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);