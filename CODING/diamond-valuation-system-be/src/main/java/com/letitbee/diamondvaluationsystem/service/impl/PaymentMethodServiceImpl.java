package com.letitbee.diamondvaluationsystem.service.impl;

import com.letitbee.diamondvaluationsystem.entity.PaymentMethod;
import com.letitbee.diamondvaluationsystem.payload.PaymentDTO;
import com.letitbee.diamondvaluationsystem.payload.PaymentMethodDTO;
import com.letitbee.diamondvaluationsystem.repository.PaymentMethodRepository;
import com.letitbee.diamondvaluationsystem.service.PaymentMethodService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private ModelMapper mapper;
    private PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodServiceImpl(ModelMapper mapper, PaymentMethodRepository paymentMethodRepository) {
        this.mapper = mapper;
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public PaymentMethodDTO createPaymentMethod(PaymentMethodDTO paymentMethodDTO) {
        PaymentMethod paymentMethod = mapToEntity(paymentMethodDTO);
        paymentMethod = paymentMethodRepository.save(paymentMethod);
        return mapToDTO(paymentMethod);
    }

    @Override
    public List<PaymentMethodDTO> getAllPaymentMethod() {
        List<PaymentMethodDTO> paymentMethodDTOS = paymentMethodRepository.findAll()
                .stream().map(paymentMethod ->mapToDTO(paymentMethod)).toList();
        return paymentMethodDTOS;
    }

    private PaymentMethod mapToEntity(PaymentMethodDTO paymentMethodDTO) {
        return mapper.map(paymentMethodDTO, PaymentMethod.class);
    }

    private PaymentMethodDTO mapToDTO(PaymentMethod paymentMethod) {
        return mapper.map(paymentMethod, PaymentMethodDTO.class);
    }
}
