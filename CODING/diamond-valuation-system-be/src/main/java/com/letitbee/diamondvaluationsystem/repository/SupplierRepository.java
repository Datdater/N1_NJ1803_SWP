package com.letitbee.diamondvaluationsystem.repository;

import com.letitbee.diamondvaluationsystem.entity.DiamondMarket;
import com.letitbee.diamondvaluationsystem.entity.DiamondValuationNote;
import com.letitbee.diamondvaluationsystem.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("SELECT d.id " +
            "FROM DiamondMarket d " +
            "WHERE d.supplier = ?1")
    Set<Long> findAllDiamond(Supplier supplier);
}
