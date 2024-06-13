package com.letitbee.diamondvaluationsystem.repository;

import com.letitbee.diamondvaluationsystem.entity.DiamondValuationAssign;
import com.letitbee.diamondvaluationsystem.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface DiamondValuationAssignRepository extends JpaRepository<DiamondValuationAssign, Long> {
    int countDiamondValuationAssignByStaff(Staff staff);

    @Query("SELECT d.id " +
            "FROM DiamondValuationAssign d " +
            "WHERE d.staff = :staff ")
    Set<Long> findAllByStaff(Staff staff);

    @Query(value = "SELECT count(df.diamond_id) as diamond_count " +
            "FROM (" +
            "       SELECT d.id as diamond_id, d.staff as staff " +
            "       FROM DiamondValuationAssign d " +
            "       WHERE d.status = false) df " +
            "RIGHT JOIN Staff s ON s = df.staff " +
            "WHERE s = :staff " +
            "group by s.id ")
    int countDiamondValuationAssignIsProcessedByStaff(@Param("staff") Staff staff);
}
