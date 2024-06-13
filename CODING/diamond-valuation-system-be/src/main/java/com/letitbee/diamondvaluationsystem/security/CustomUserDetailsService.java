//package com.letitbee.diamondvaluationsystem.security;
//
//import com.letitbee.diamondvaluationsystem.entity.Account;
//import com.letitbee.diamondvaluationsystem.repository.AccountRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//    private AccountRepository accountRepository;
//
//    @Autowired
//    public CustomUserDetailsService(AccountRepository accountRepository) {
//        this.accountRepository = accountRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Account account = accountRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email : " + username));
//
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        authorities.add(new SimpleGrantedAuthority(account.getRole().name()));
//        return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(), authorities);
//    }
//}
