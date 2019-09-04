package com.eigenbaumarkt.sync2datasources.service;

import com.eigenbaumarkt.sync2datasources.dao.UserDaoImpl;
import com.eigenbaumarkt.sync2datasources.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDaoImpl userDao;

    public List<User> getAllUser() {
        return userDao.getAllUser();
    }
}
