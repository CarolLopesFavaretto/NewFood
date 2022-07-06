package com.newfood.delivery.infrastructure.repository;

import com.newfood.delivery.domain.model.Payment;
import com.newfood.delivery.domain.repository.PaymentRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PaymentRepositoryImp implements PaymentRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Payment> list() {
        return manager.createQuery("from Payment", Payment.class).getResultList();
    }

    @Override
    public Payment findById(Long id) {
        return manager.find(Payment.class, id);
    }

    @Override
    @Transactional
    public Payment save(Payment payment) {
        return manager.merge(payment);
    }

    @Override
    public Payment update(Payment payment) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Payment payment) {
        payment = findById(payment.getId());
        manager.remove(payment);
    }
}
