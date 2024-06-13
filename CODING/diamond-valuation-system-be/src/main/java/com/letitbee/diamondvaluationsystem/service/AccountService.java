package com.letitbee.diamondvaluationsystem.service;

import com.letitbee.diamondvaluationsystem.payload.AccountDTO;
import com.letitbee.diamondvaluationsystem.payload.AccountResponse;

public interface AccountService {

    String login(AccountDTO accountDTO);

    AccountResponse register(AccountDTO accountDTO);

    String updatePassword(String newPassword, Long id);
}
