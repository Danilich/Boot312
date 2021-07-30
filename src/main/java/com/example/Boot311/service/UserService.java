package com.example.Boot311.service;





import com.example.Boot311.models.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void edit(User user);

    void delete(long id);

    User getUserById(long id);

    List<User> listUsers();

}
