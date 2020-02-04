package ru.otus.hw13.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.otus.hw13.core.sessionmanager.SessionManager;
import ru.otus.hw13.core.sessionmanager.hibernate.DatabaseSessionHibernate;
import ru.otus.hw13.core.sessionmanager.hibernate.SessionManagerHibernate;
import ru.otus.hw13.domain.User;

@Repository
public class HibernateUserRepository implements UserRepository {
    private static Logger logger = LoggerFactory.getLogger(HibernateUserRepository.class);
    private final SessionManagerHibernate sessionManager;

    public HibernateUserRepository(SessionManagerHibernate sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public Optional<User> save(User user) {
        DatabaseSessionHibernate currentSession = sessionManager.getCurrentSession();

        try {
            Session hibernateSession = currentSession.getHibernateSession();

            if (user.getId() > 0) {
                hibernateSession.merge(user);
            } else {
                hibernateSession.persist(user);
            }
            return Optional.of(user);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        try {
            return sessionManager.getCurrentSession()
                    .getHibernateSession()
                    .createQuery("select u from User u", User.class)
                    .getResultList();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return new ArrayList<>();
    }

    @Override
    public Optional<User> getByLogin(String login) {
        try {
            return Optional.of(
                    sessionManager.getCurrentSession()
                            .getHibernateSession()
                            .createQuery("" +
                                    "select u " +
                                    "from User u " +
                                    "where u.login = :login", User.class)
                            .setParameter("login", login)
                            .getSingleResult()
            );
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return Optional.empty();
    }

    @Override
    public SessionManager sessionManager() {
        return sessionManager;
    }
}