package com.newfood.delivery.domain.repository;

import com.newfood.delivery.domain.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
