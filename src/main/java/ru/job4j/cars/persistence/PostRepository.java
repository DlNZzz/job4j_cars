package ru.job4j.cars.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.cars.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.function.Function;

/*
В нем через Hibernate на HQL напишите три метода для извлечения данных.

- показать объявления за последний день;

- показать объявления с фото;

- показать объявления определенной марки.
 */
public class PostRepository {
    private final SessionFactory sessionFactory;

    public PostRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Collection<Post> findAvailabilityOfPhotos() {
        return this.tx(
                session -> {
                    Query query = session.createQuery("from Post p where p.created >= :day");
                    query.setParameter("day", LocalDateTime.now().minusDays(1));
                    return query.list();
                }
        );
    }

    public Collection<Post> findLastDay() {
        return this.tx(
                session -> session.createQuery("")
                        .list()
        );
    }

    public Collection<Post> findMark(String brand) {
        return this.tx(
                session -> session.createQuery("")
                        .list()
        );
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sessionFactory.openSession();
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
