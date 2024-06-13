package com.letitbee.diamondvaluationsystem.repository;

import com.letitbee.diamondvaluationsystem.entity.Account;
import com.letitbee.diamondvaluationsystem.entity.Staff;
import com.letitbee.diamondvaluationsystem.entity.ValuationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Optional<Staff> findStaffByValuationRequestSetContaining(ValuationRequest valuationRequest);

    Optional<Staff> findStaffByAccount(Account account);

    List<Staff> findStaffByFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(String firstName, String lastName);

}
