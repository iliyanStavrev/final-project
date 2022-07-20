package com.example.sportclopedia.service;

import com.example.sportclopedia.model.dto.RegisterDto;
import com.example.sportclopedia.model.entity.User;

public interface UserService {

    public void init();

    void registerAndLogin(RegisterDto registerDto);

    User findByUsername(String username);

}
