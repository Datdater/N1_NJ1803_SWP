package com.letitbee.diamondvaluationsystem.repository;

import com.letitbee.diamondvaluationsystem.entity.DiamondValuationNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DiamondValuationNoteRepository extends JpaRepository<DiamondValuationNote, Long> {

    Optional<DiamondValuationNote> findByCertificateId(String certificateId);

    @Query(value = "select count(*) " +
            "from DiamondValuationNote d " +
            "where d.certificateId = :certificateId")
    int countByCertificateId(String certificateId);
}
