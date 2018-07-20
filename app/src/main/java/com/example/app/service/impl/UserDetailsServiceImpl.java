package com.example.app.service.impl;

import com.example.app.domain.Identity;
import com.example.app.repository.IdentityRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final String AUTHENTICATION_FAILED = "invalid login or/and password";

    @Autowired
    private IdentityRepository identityRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String login) throws UsernameNotFoundException {
        Identity identity = identityRepository.findByLogin(login);
        if (identity == null) {
            throw new UsernameNotFoundException(AUTHENTICATION_FAILED);
        }
        Set<GrantedAuthority> authorities = SetUtils.emptyIfNull(Stream.of(StringUtils.defaultIfEmpty(identity.getRoleString(),"")
                .split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toSet()));
        return new User(identity.getLogin(), identity.getPassword(), authorities);
    }
}
