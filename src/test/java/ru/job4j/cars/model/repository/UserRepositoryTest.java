package ru.job4j.cars.model.repository;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;
import ru.job4j.cars.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;

public class UserRepositoryTest {

    StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();
    CrudRepository crudRepository = new CrudRepository(sf);

    @Test
    public void findAvailabilityOfPhotos() throws Exception {
        var repository = new UserRepository(crudRepository);
        User user = new User();
        user.setLogin("qwe");
        repository.create(user);
        User received = repository.findById(user.getId()).get();
        assertThat(received, is(user));
    }

    @Test
    public void testUpdate() {
        var repository = new UserRepository(crudRepository);
        User user = new User();
        user.setLogin("qwe");
        repository.create(user);
        user.setLogin("asd");
        repository.update(user);
        User received = repository.findById(user.getId()).get();
        assertThat(received, is(user));
    }

    @Test
    public void testDelete() {
        var repository = new UserRepository(crudRepository);
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setLogin("qwe");
        repository.create(user);
        repository.delete(user.getId());
        List<User> received = repository.findAllOrderById();
        assertThat(received, is(list));
    }

    @Test
    public void testFindAllOrderById() {
        var repository = new UserRepository(crudRepository);
        List<User> list = new ArrayList<>();
        User user = new User();
        User user1 = new User();
        list.add(user);
        list.add(user1);
        user.setLogin("qwe");
        repository.create(user);
        repository.create(user1);
        List<User> received = repository.findAllOrderById();
        assertThat(received, is(list));
    }

    @Test
    public void testFindById() {
        var repository = new UserRepository(crudRepository);
        User user = new User();
        user.setLogin("qwe");
        repository.create(user);
        User received = repository.findById(user.getId()).get();
        assertThat(received, is(user));
    }

    @Test
    public void testFindByLikeLogin() {
        var repository = new UserRepository(crudRepository);
        List<User> list = new ArrayList<>();
        User user = new User();
        list.add(user);
        user.setLogin("qwe");
        repository.create(user);
        List<User> received = repository.findByLikeLogin("w");
        assertThat(received, is(list));
    }

    @Test
    public void testFindByLogin() {
        var repository = new UserRepository(crudRepository);
        User user = new User();
        user.setLogin("qwe");
        repository.create(user);
        User received = repository.findByLogin("qwe").get();
        assertThat(received, is(user));
    }
}