package com.letitbee.diamondvaluationsystem.service;

import com.letitbee.diamondvaluationsystem.entity.DiamondValuationAssign;
import com.letitbee.diamondvaluationsystem.payload.DiamondValuationAssignDTO;
import com.letitbee.diamondvaluationsystem.payload.Response;

public interface DiamondValuationAssignService {
    DiamondValuationAssignDTO createDiamondValuationAssign(DiamondValuationAssignDTO diamondValuationAssignDTO);
    DiamondValuationAssignDTO updateDiamondValuationAssign(long id, DiamondValuationAssignDTO diamondValuationAssignDTO);
    Response<DiamondValuationAssignDTO> getAllDiamondValuationAssign(int pageNo, int pageSize, String sortBy, String sortDir);
    DiamondValuationAssignDTO getDiamondValuationAssignById(long id);
}
