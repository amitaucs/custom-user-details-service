package com.amisoft.customuserdetails.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;

@Configuration
public class BeanConfiguration {


    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    CustomUserDetailsService customUserDetailsService(){

        Collection<UserDetails> users = Arrays.asList(

                new CustomUserDetails("amisoft", "password2",true,"USER"),
                new CustomUserDetails("amit", "password3",true,"ADMIN")
        );

        return new CustomUserDetailsService(users);

    }


}
