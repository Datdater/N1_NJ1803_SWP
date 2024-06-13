package com.letitbee.diamondvaluationsystem.controller;

import com.letitbee.diamondvaluationsystem.payload.PaymentMethodDTO;
import com.letitbee.diamondvaluationsystem.repository.PaymentMethodRepository;
import com.letitbee.diamondvaluationsystem.service.PaymentMethodService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payment-methods")
public class PaymentMethodController {
    private PaymentMethodService methodService;

    public PaymentMethodController(PaymentMethodService methodService) {
        this.methodService = methodService;
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethodDTO>> getAllPaymentMethod() {
        return new ResponseEntity<>(methodService.getAllPaymentMethod(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<PaymentMethodDTO> createPaymentMethod(@RequestBody @Valid PaymentMethodDTO paymentMethodDTO) {
        return new ResponseEntity<>(methodService.createPaymentMethod(paymentMethodDTO), HttpStatus.CREATED);
    }
}
