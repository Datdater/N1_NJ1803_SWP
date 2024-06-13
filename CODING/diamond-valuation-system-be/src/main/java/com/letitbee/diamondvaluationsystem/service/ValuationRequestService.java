package com.letitbee.diamondvaluationsystem.service;

import com.letitbee.diamondvaluationsystem.enums.RequestStatus;
import com.letitbee.diamondvaluationsystem.payload.Response;
import com.letitbee.diamondvaluationsystem.payload.ValuationRequestDTO;
import com.letitbee.diamondvaluationsystem.payload.ValuationRequestResponse;
import com.letitbee.diamondvaluationsystem.payload.ValuationRequestResponseV2;

import java.util.Date;

public interface ValuationRequestService {

    Response<ValuationRequestResponse> getAllValuationRequests(int pageNo, int pageSize, String sortBy, String sortDir, Date startDate, Date endDate);

    ValuationRequestDTO getValuationRequestById(Long id);

    ValuationRequestDTO createValuationRequest(ValuationRequestDTO valuationRequestDto);

    ValuationRequestDTO updateValuationRequest(long id, ValuationRequestDTO valuationRequestDTO);
    ValuationRequestDTO deleteValuationRequestById(Long id);

    Response<ValuationRequestResponseV2> getValuationRequestResponse(
            int pageNo, int pageSize, String sortBy, String sortDir, RequestStatus status);

}
