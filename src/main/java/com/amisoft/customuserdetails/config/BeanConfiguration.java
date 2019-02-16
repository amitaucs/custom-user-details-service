package com.amisoft.customuserdetails.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Configuration
public class BeanConfiguration {


    @Bean
    PasswordEncoder oldPasswordEncoder(){

        String md5 = "MD5";

        return new DelegatingPasswordEncoder(md5,
                Collections.singletonMap(md5,new MessageDigestPasswordEncoder(md5)));
    }



    @Bean
    CustomUserDetailsService customUserDetailsService(){

        Collection<UserDetails> users = Arrays.asList(

                new CustomUserDetails("amisoft", oldPasswordEncoder().encode("password5"),true,"USER"),
                new CustomUserDetails("amit", oldPasswordEncoder().encode("password3"),true,"ADMIN")
        );

        return new CustomUserDetailsService(users);

    }


}
