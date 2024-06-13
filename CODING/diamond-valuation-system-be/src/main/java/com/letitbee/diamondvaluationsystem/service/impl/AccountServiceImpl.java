package com.letitbee.diamondvaluationsystem.service.impl;

import com.letitbee.diamondvaluationsystem.entity.Account;
import com.letitbee.diamondvaluationsystem.exception.APIException;
import com.letitbee.diamondvaluationsystem.exception.ResourceNotFoundException;
import com.letitbee.diamondvaluationsystem.payload.AccountDTO;
import com.letitbee.diamondvaluationsystem.payload.AccountResponse;
import com.letitbee.diamondvaluationsystem.repository.AccountRepository;
//import com.letitbee.diamondvaluationsystem.security.JwtTokenProvider;
import com.letitbee.diamondvaluationsystem.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
//    private PasswordEncoder passwordEncoder;
//    private JwtTokenProvider jwtTokenProvider;
//    private AuthenticationManager authenticationManager;

    private ModelMapper mapper;

    public AccountServiceImpl(AccountRepository accountRepository, ModelMapper mapper
                              //JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager,
                             /* PasswordEncoder passwordEncoder*/) {
        this.accountRepository = accountRepository;
        this.mapper = mapper;
//        this.jwtTokenProvider = jwtTokenProvider;
//        this.authenticationManager = authenticationManager;
//        this.passwordEncoder = passwordEncoder;
    }
    private AccountDTO mapToDto(Account account){
        AccountDTO accountDto = mapper.map(account, AccountDTO.class);
        return accountDto;
    }
    //convert DTO to Entity
    private Account mapToEntity(AccountDTO accountDto){
        Account account = mapper.map(accountDto, Account.class);
        return account;
    }

    @Override
    public String login(AccountDTO accountDTO) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(accountDTO.getUsername(), accountDTO.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = jwtTokenProvider.generateToken(authentication);
        return "token";
    }

    @Override
    public AccountResponse register(AccountDTO accountDTO) {
        //add check for username exists in database
        if (accountRepository.existsByUsername(accountDTO.getUsername())){
            throw new APIException(HttpStatus.BAD_REQUEST, "Account is already taken");
        }

        //save account to db
        Account account = new Account();
        account.setUsername(accountDTO.getUsername());
//        account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        account.setPassword(accountDTO.getPassword());
        account.setRole(accountDTO.getRole());
        account.setIs_active(true);
        account = accountRepository.save(account);

        //return account to client without password
        AccountResponse newAccount = new AccountResponse();
        newAccount.setId(account.getId());
        newAccount.setUsername(account.getUsername());
        newAccount.setRole(account.getRole());
        newAccount.setIs_active(account.getIs_active());
        return newAccount;
    }

    @Override
    public String updatePassword(String newPassword, Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "id", String.valueOf(id)));
        //happycase
        account.setPassword(newPassword);
        accountRepository.save(account);
        return "Update password successfully";
    }


}