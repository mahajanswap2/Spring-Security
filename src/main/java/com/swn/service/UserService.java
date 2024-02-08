package com.swn.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.swn.modal.User;
import com.swn.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

	User save(UserRegistrationDto registrationDto);
}
