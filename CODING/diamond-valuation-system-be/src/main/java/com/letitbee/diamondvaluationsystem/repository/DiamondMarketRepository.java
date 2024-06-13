package com.letitbee.diamondvaluationsystem.repository;

import com.letitbee.diamondvaluationsystem.entity.DiamondMarket;
import com.letitbee.diamondvaluationsystem.enums.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiamondMarketRepository extends JpaRepository<DiamondMarket, Long> {
    @Query("SELECT d " +
            "FROM DiamondMarket d " +
            "WHERE d.diamondOrigin = :diamondOrigin " +
            "AND d.caratWeight between :caratWeight - 0.15 and :caratWeight + 0.15 " +
            "AND d.color = :color " +
            "AND d.clarity = :clarity " +
            "AND d.cut = :cut " +
            "AND d.polish = :polish " +
            "AND d.symmetry = :symmetry " +
            "AND d.shape = :shape " +
            "AND d.fluorescence = :fluorescence " +
            "ORDER BY d.creationDate DESC, d.price ASC")
    List<DiamondMarket> findSelectedFieldsByDiamondProperties(
            @Param("diamondOrigin") DiamondOrigin diamondOrigin,
            @Param("caratWeight") float caratWeight,
            @Param("color") Color color,
            @Param("clarity") Clarity clarity,
            @Param("cut") Cut cut,
            @Param("polish") Polish polish,
            @Param("symmetry") Symmetry symmetry,
            @Param("shape") Shape shape,
            @Param("fluorescence") Fluorescence fluorescence);

}
