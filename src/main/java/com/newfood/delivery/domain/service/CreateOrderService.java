package com.newfood.delivery.domain.service;

import com.newfood.delivery.api.exceptions.BusinessException;
import com.newfood.delivery.api.exceptions.OrderNotFoundException;
import com.newfood.delivery.domain.model.*;
import com.newfood.delivery.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CreateOrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private CreateUserService userService;

    @Autowired
    private CreatePaymentService paymentService;

    @Autowired
    private CreateProductService createProductService;

    @Autowired
    private CreateCityService cityService;

    @Autowired
    private CreateRestaurantService restaurantService;

    @Transactional
    public Orders emitir(Orders orders) {
        validOrder(orders);
        validItens(orders);

        orders.setFeeShipping(orders.getRestaurant().getShipping());
        orders.calculateTotal();

        return repository.save(orders);
    }

    private void validOrder(Orders orders) {
        City city = cityService.findById(orders.getAddress().getCity().getId());
        User user = userService.findById(orders.getUser().getId());
        Restaurant restaurant = restaurantService.findById(orders.getRestaurant().getId());
        Payment payment = paymentService.findById(orders.getPayment().getId());

        orders.getAddress().setCity(city);
        orders.setUser(user);
        orders.setRestaurant(restaurant);
        orders.setPayment(payment);

        if (restaurant.doesNotAcceptPaymentMethod(payment)) {
            throw new BusinessException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.",
                    payment.getDescription()));
        }
    }

    private void validItens(Orders orders) {
        orders.getItems().forEach(item -> {
            Product product = createProductService.findById(
                    orders.getRestaurant().getId(), item.getProduct().getId());

            item.setOrders(orders);
            item.setProduct(product);
            item.setValue(product.getPrice());
        });
    }

    public Orders findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new OrderNotFoundException(String.format("O código %d informado não foi encontrado", id)));
    }
}
