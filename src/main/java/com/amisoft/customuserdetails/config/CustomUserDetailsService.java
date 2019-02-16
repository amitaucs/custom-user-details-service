package com.amisoft.customuserdetails.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//For simplicity added it as a Bean in main application to load user
//@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {


    private final Map<String,UserDetails> users = new ConcurrentHashMap<>();

   //Simplicity purpose user stored in map. Production it may load for no sql db.
    public CustomUserDetailsService (Collection<UserDetails> seedUsers) {

        seedUsers.forEach(user-> this.users.put(user.getUsername(),user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Make DB call here in production code
        if( this.users.containsKey(username)){
            return this.users.get(username);
        }

        throw new UsernameNotFoundException("Couldn't find  : "+username);
    }
}
