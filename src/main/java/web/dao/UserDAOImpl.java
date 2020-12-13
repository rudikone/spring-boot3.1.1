package web.dao;

import org.springframework.stereotype.Repository;
import web.models.User;
import web.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

//@Component
@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> typedQuery = entityManager.createQuery("from User", User.class);
        return typedQuery.getResultList();
    }

    @Override
    public User show(int id) {
        User person = entityManager.find(User.class, id);
        return person;
    }

    @Override
    public void update(int id, User updatePerson) {
        User personToBeUpdated = entityManager.find(User.class, id);
        Set<Role> roles = personToBeUpdated.getRoles();
        roles.clear();
        personToBeUpdated.setRoles(roles);
        personToBeUpdated.setName(updatePerson.getName());
        personToBeUpdated.setPassword(updatePerson.getPassword());
        personToBeUpdated.setRoles(updatePerson.getRoles());
        entityManager.merge(personToBeUpdated);
    }

    @Override
    public void save(User person) {
        entityManager.persist(person);
    }

    @Override
    public void delete(int id) {
        User person = entityManager.find(User.class, id);
        entityManager.remove(person);
    }

    @Override
    public User getUserByName(String name) {

//        return entityManager.find(User.class, name);

        return entityManager.createQuery("FROM User U WHERE U.name = :paramName", User.class).setParameter("paramName", name).getSingleResult();
    }
}

