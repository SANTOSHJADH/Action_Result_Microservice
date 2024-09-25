package com.collection.ActionResult.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.collection.ActionResult.Entity.UserEntity;
import com.collection.ActionResult.Repository.UserRepository;

@Service
public class UserServiceImpl {

	@Autowired
	private UserRepository userrepo;
	@Autowired
	private JWTService jwtService;

	/**
	 * AuthenticationManager
	 */
	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * userDetailsService
	 */
	@Autowired
	private UserDetailsService userDetailsService;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	public UserEntity addUser(UserEntity user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return userrepo.save(user);
	}

	public String verify(UserEntity user) {
//		org.springframework.security.core.Authentication authentication = authManager
//				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		this.authenticate(user.getUsername(), user.getPassword());
		org.springframework.security.core.userdetails.UserDetails userDetails = this.userDetailsService
				.loadUserByUsername(user.getUsername());
		if (userDetails != null) {
			return jwtService.generateToken(user.getUsername());
		} else {
			return "fail";
		}
	}

	/**
	 * @param username
	 * @param password
	 */
	private void authenticate(String username, String password) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);
		this.authenticationManager.authenticate(authenticationToken);
	}

}
