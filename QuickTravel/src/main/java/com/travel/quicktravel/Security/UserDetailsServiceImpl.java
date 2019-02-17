package com.travel.quicktravel.Security;

import com.travel.quicktravel.Repositories.AccountRepository;
import com.travel.quicktravel.entities.Account;
import com.travel.quicktravel.entities.Role;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account  = accountRepository.findByEmail(login);
        
        if (account == null) {
            throw new UsernameNotFoundException(login);
        }

        Collection<GrantedAuthority> autorities = new ArrayList<>();
        Role role = account.getRole();
        autorities.add(new SimpleGrantedAuthority(role.getCode()));
        return new User(account.getEmail(), account.getPassword(), autorities);
    }

}
