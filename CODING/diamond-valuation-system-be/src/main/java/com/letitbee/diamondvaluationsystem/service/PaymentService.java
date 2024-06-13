package com.letitbee.diamondvaluationsystem.service;

import com.letitbee.diamondvaluationsystem.payload.PaymentDTO;
import com.letitbee.diamondvaluationsystem.payload.Response;

public interface PaymentService {
    PaymentDTO createPayment(PaymentDTO paymentDTO);
    Response<PaymentDTO> getAllPayment(int pageNo, int pageSize, String sortBy, String sortDir);
}

