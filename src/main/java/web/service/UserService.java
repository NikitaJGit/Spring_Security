package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUser(long id);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(long id);

}
