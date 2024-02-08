package com.swn.serviceimpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.swn.modal.Role;
import com.swn.modal.User;
import com.swn.repository.UserRepository;
import com.swn.service.UserService;
import com.swn.web.dto.UserRegistrationDto;


@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserRepository userRepository ;
	

	@Autowired
	private BCryptPasswordEncoder  passwordEncoder;
	
	
	
    public UserServiceImpl(BCryptPasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public UserServiceImpl() {
	
	}


	@Override
	public User save(UserRegistrationDto registrationDto) {
		
		// don't pass plain-text password
		// encode the password using passwordEncoder
		
		String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());
		
		User user = new User(
					registrationDto.getFirstName(),
					registrationDto.getLastName(),
					registrationDto.getEmail(),
					encodedPassword,
					Arrays.asList(new Role("ROLE_USER")));
	
		return userRepository.save(user);
	}





	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		UserDetails userDetails = null;
	    System.out.println("username--->>>"+username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		System.out.println("Password--->>>"+user.getPassword());
		userDetails = new org.springframework.security.core.userdetails.User (
							user.getEmail(), 
							user.getPassword(), 
							mapRolesToAuthorities(user.getRoles()));
		
		return userDetails;
	}
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
