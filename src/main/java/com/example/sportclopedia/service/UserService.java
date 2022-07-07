package com.example.sportclopedia.service;

import com.example.sportclopedia.model.dto.RegisterDto;

public interface UserService {

    public void init();

    void registerAndLogin(RegisterDto registerDto);
}
