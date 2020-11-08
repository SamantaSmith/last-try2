package org.ivalex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> listAll() {
        return (List<User>) repo.findAll();
    }

    public void add(User user) {
        repo.save(user);
    }

    public User showById(Long id) {
        Optional<User> optionalUser = repo.findById(id);
        User user = optionalUser.orElse(null);
        return user;
    }
}
