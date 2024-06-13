package com.letitbee.diamondvaluationsystem.service;

import com.letitbee.diamondvaluationsystem.entity.ValuationRequestDetail;
import com.letitbee.diamondvaluationsystem.payload.Response;
import com.letitbee.diamondvaluationsystem.payload.ValuationRequestDetailDTO;

public interface ValuationRequestDetailService {
    Response<ValuationRequestDetailDTO> getAllValuationRequestDetail(int pageNo, int pageSize, String sortBy, String sortDir);

    ValuationRequestDetailDTO getValuationRequestDetailById(Long id);

    ValuationRequestDetailDTO updateValuationRequestDetail(long id, ValuationRequestDetailDTO valuationRequestDetailDTO);

}
