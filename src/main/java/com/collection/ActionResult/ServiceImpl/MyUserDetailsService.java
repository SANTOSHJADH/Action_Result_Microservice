package com.collection.ActionResult.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.collection.ActionResult.Entity.UserEntity;
import com.collection.ActionResult.Entity.UserPrinciple;
import com.collection.ActionResult.Repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
    private UserRepository userrepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user=userrepo.findByUsername(username);
        if(user==null)
        {
        	throw new UsernameNotFoundException("user not found");
        }
        	
        return new UserPrinciple(user) ;
	}

}
