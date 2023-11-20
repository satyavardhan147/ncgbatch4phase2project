package com.example.fms.service;

import com.example.fms.entity.LoginMaster;
import com.example.fms.repository.ILoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements ILoginService {
    @Autowired
    private ILoginRepository loginRepository;
    public boolean doLogin(String email, String password) {
        Optional<LoginMaster> login = loginRepository.findByEmail(email);
        return login.map(l -> l.getPassword().equals(password)).orElse(false);
    }
}
