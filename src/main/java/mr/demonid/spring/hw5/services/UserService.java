package mr.demonid.spring.hw5.services;

import mr.demonid.spring.hw5.domain.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User addUser(User user);

    void deleteUser(Long id);

}
