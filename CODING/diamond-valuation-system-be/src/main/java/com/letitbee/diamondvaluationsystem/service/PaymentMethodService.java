package com.letitbee.diamondvaluationsystem.service;

import com.letitbee.diamondvaluationsystem.entity.PaymentMethod;
import com.letitbee.diamondvaluationsystem.payload.PaymentDTO;
import com.letitbee.diamondvaluationsystem.payload.PaymentMethodDTO;

import java.util.List;

public interface PaymentMethodService {
    PaymentMethodDTO createPaymentMethod(PaymentMethodDTO paymentMethodDTO);
    List<PaymentMethodDTO> getAllPaymentMethod();
}
