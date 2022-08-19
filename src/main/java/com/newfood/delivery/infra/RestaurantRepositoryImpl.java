package com.newfood.delivery.infra;

import com.newfood.delivery.domain.model.Restaurant;
import com.newfood.delivery.domain.repository.RestaurantRepositoryQuerys;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQuerys {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurant> find(String name, BigDecimal shippingBegin, BigDecimal shippingFinal) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();

        CriteriaQuery<Restaurant> criteriaQuery = builder.createQuery(Restaurant.class);
        Root<Restaurant> root = criteriaQuery.from(Restaurant.class); // == "from Restaurant"

        Predicate namePredicate = builder.like(root.get("name"), "%" + name +"%");
        Predicate shippingPredicate = builder.greaterThanOrEqualTo(root.get("shipping"), shippingBegin);
        Predicate shippingFinalPredicate = builder.lessThanOrEqualTo(root.get("shipping"), shippingFinal);

        criteriaQuery.where(namePredicate, shippingPredicate, shippingFinalPredicate);

        TypedQuery<Restaurant> query = manager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
