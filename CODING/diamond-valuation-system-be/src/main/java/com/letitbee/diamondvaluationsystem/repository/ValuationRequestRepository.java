package com.letitbee.diamondvaluationsystem.repository;

import com.letitbee.diamondvaluationsystem.entity.Customer;
import com.letitbee.diamondvaluationsystem.entity.Staff;
import com.letitbee.diamondvaluationsystem.entity.ValuationRequest;
import com.letitbee.diamondvaluationsystem.enums.RequestStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

public interface ValuationRequestRepository extends JpaRepository<ValuationRequest, Long> {
    @Query("SELECT v.id " +
            "FROM ValuationRequest v " +
            "WHERE v.customer = :customer ")
    Set<Long> findAllByCustomer(Customer customer);

    Page<ValuationRequest> findByCreationDateBetween(Date startDate, Date endDate, Pageable pageable);

    @Query("SELECT v.id " +
            "FROM ValuationRequest v " +
            "WHERE v.staff = :staff ")
    Set<Long> findAllByStaff(Staff staff);
    int countValuationRequestsByStaff(Staff staff);

    @Query(value = "select count(vf.valuation_id) as valuation_request_count " +
            "from (" +
                    "select v.id as valuation_id, v.staff as staff from ValuationRequest v " +
                    "where v.status != 'FINISHED') vf " +
            "right join Staff s on s = vf.staff " +
            "where s = :staff " +
            "group by s.id")
    int countValuationRequestsIsProcessedByStaff(Staff staff);


    Page<ValuationRequest> findAllByStatus(RequestStatus status, Pageable pageable);
}
