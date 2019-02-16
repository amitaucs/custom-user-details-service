package com.amisoft.customuserdetails.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

//For simplicity added it as a Bean in main application to load user
//@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService, UserDetailsPasswordService {


    private final Map<String,UserDetails> users = new ConcurrentHashMap<>();

   //Simplicity purpose user stored in map. Production it may load for no sql db.
    public CustomUserDetailsService (Collection<UserDetails> seedUsers) {

        seedUsers.forEach(user-> this.users.put(user.getUsername(),user));
        this.users.forEach((k,v) -> log.info(k + "=" +v.getPassword()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Make DB call here in production code
        if( this.users.containsKey(username)){
            return this.users.get(username);
        }

        throw new UsernameNotFoundException("Couldn't find  : "+username);
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {

        log.info("prompted to update password for user "+user.getUsername() + " to " + newPassword);

        this.users.put(user.getUsername(), new CustomUserDetails(
                user.getUsername(),
                newPassword,
                user.isEnabled(),
                user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[] :: new)));

        return this.loadUserByUsername(user.getUsername());
    }
}
