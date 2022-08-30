package com.chukwuma.timemanagementapp.service;

import com.chukwuma.timemanagementapp.model.User;
import org.springframework.stereotype.Service;


public interface UserService {

    User save(User user);

    User login(String email, String password);
}
