package com.chukwuma.timemanagementapp.serviceImpl;

import com.chukwuma.timemanagementapp.model.User;
import com.chukwuma.timemanagementapp.repository.UserRepository;
import com.chukwuma.timemanagementapp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User save(User user) {
        User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        return userRepo.save(newUser);
    }

    @Override
    public User login(String email, String password) {
        return userRepo.findUserByEmailAndPassword(email, password);
    }
}
