package com.ivodrag.springbootusers.service;

import com.ivodrag.springbootusers.entity.User;
import com.ivodrag.springbootusers.exception.UserNotFoundException;
import com.ivodrag.springbootusers.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAllUsers() {
        return repository.findAll();
    }

    public void saveUser(User user) {
        repository.save(user);
    }

    public User get(int id) throws UserNotFoundException {
        Optional<User> theUser = repository.findById(id);
        if(theUser.isPresent()) {
            return theUser.get();
        }
        else {
            throw new UserNotFoundException("Could not find user with ID (" + id + ")");
        }
    }

    public void deleteUser(int id) throws UserNotFoundException {
        Optional<User> theUser = repository.findById(id);
        if(theUser.isPresent()) {
            repository.deleteById(id);
        }
        else {
            throw new UserNotFoundException("Could not find user with ID (" + id + ")");
        }
    }
}
