package com.newfood.delivery.api.controller;

import com.newfood.delivery.domain.model.Payment;
import com.newfood.delivery.domain.repository.PaymentRepository;
import com.newfood.delivery.domain.service.CreatePaymentService;
import com.newfood.delivery.dto.PaymentDTO;
import com.newfood.delivery.dto.request.PaymentRequest;
import com.newfood.delivery.dto.response.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private CreatePaymentService service;

    @Autowired
    private PaymentDTO dto;


    @GetMapping
    public List<PaymentResponse> findAll() {
        List<Payment> payments = repository.findAll();
        return dto.toCollectionModel(payments);
    }

    @GetMapping("/{id}")
    public PaymentResponse findById(@PathVariable Long id) {
        Payment payment = service.findById(id);
        return dto.toModel(payment);
    }

    @PostMapping
    public PaymentResponse created(@RequestBody @Valid PaymentRequest request) {
        Payment payment = dto.toObject(request);
        return dto.toModel(service.save(payment));
    }

    @PutMapping("/{id}")
    public PaymentResponse updated(@RequestBody @Valid PaymentRequest request, @PathVariable Long id) {
        Payment newPayment = service.findById(id);
        dto.updatedToObject(request, newPayment);
        return dto.toModel(service.save(newPayment));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
