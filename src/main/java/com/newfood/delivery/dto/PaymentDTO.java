package com.newfood.delivery.dto;

import com.newfood.delivery.domain.model.Payment;
import com.newfood.delivery.dto.request.PaymentRequest;
import com.newfood.delivery.dto.response.PaymentResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentDTO {

    @Autowired
    private ModelMapper mapper;

    public Payment toObject(PaymentRequest request) {
        return mapper.map(request, Payment.class);
    }

    public PaymentResponse toModel(Payment payment) {
        return mapper.map(payment, PaymentResponse.class);
    }

    public List<PaymentResponse> toCollectionModel(Collection<Payment> payments) {
        return payments.stream()
                .map(payment -> toModel(payment))
                .collect(Collectors.toList());
    }

    public void updatedToObject(PaymentRequest request, Payment payment) {
        payment.setDescription(new String());
        mapper.map(request, payment);
    }
}
