package com.amisoft.customuserdetails.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;

@Configuration
public class BeanConfiguration {


    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    CustomUserDetailsService customUserDetailsService(){

        Collection<UserDetails> users = Arrays.asList(

                new CustomUserDetails("amisoft", passwordEncoder().encode("password4"),true,"USER"),
                new CustomUserDetails("amit", passwordEncoder().encode("password3"),true,"ADMIN")
        );

        return new CustomUserDetailsService(users);

    }


}
