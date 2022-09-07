package ru.job4j.cars.model.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.cars.model.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@AllArgsConstructor
public class UserRepository {
    private final SessionFactory sf;

    /**
     * Сохранить в базе.
     * @param user пользователь.
     * @return пользователь с id.
     */
    public User create(User user) {
        this.tx(
                session -> session.save(user)
        );
        return user;
    }

    /**
     * Обновить в базе пользователя.
     * @param user пользователь.
     */
    public void update(User user) {
        this.tx(
                session -> {
                    session.update(user);
                    return null;
                }
        );
    }

    /**
     * Удалить пользователя по id.
     * @param userId ID
     */
    public void delete(int userId) {
        this.tx(
                session -> {
                    User user = new User();
                    user.setId(userId);
                    session.delete(user);
                    return null;
                }
        );
    }

    /**
     * Список пользователь отсортированных по id.
     * @return список пользователей.
     */
    public List<User> findAllOrderById() {
        return this.tx(session -> session.createQuery("from User").list());
    }

    /**
     * Найти пользователя по ID
     * @return пользователь.
     */
    public Optional<User> findById(int id) {
        return Optional.of(this.tx(
                session -> session.get(User.class, id)
                ));
    }

    /**
     * Список пользователей по login LIKE %key%
     * @param key key
     * @return список пользователей.
     */
    public List<User> findByLikeLogin(String key) {
        return this.tx(session -> {
            Query query = session.createQuery("from User u where u.login like :key");
            query.setParameter("key", "%" + key + "%");
            return query.list();
        });
    }

    /**
     * Найти пользователя по login.
     * @param login login.
     * @return Optional or user.
     */
    public Optional<User> findByLogin(String login) {
        return this.tx(session -> {
            Query query = session.createQuery("from User where login = :login");
            query.setParameter("login", login);
            return query.setFirstResult(0).setMaxResults(1).uniqueResultOptional();
        });
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}