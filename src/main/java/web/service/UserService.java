package web.service;

import web.models.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public User show(Integer id);

    public void update(Integer id, User updateUser);

    public void save(User user);

    public void delete(Integer id);

    public User getUserByName(String name);
}
