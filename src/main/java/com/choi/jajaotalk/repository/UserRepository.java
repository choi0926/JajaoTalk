package com.choi.jajaotalk.repository;

import com.choi.jajaotalk.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public List<User> findUserName(String nickname) {
        return em.createQuery("select u from User u " +
                "where u.nickname = : nickname", User.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }

    public User findByNickname(String nickname) {
        return em.createQuery("select u from User u " +
                "where u.nickname = : nickname", User.class)
                .setParameter("nickname", nickname).getSingleResult();
    }
}
