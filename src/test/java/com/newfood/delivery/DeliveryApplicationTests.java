package com.newfood.delivery;

import com.newfood.delivery.api.exceptions.CuisineNotFoundException;
import com.newfood.delivery.api.exceptions.EntityNotFoundException;
import com.newfood.delivery.domain.model.Cuisine;
import com.newfood.delivery.domain.service.CreateCuisineService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class DeliveryApplicationTests {

    @Autowired
    private CreateCuisineService service;

//    @Test
//    void createdCuisine() {
//        //cenário
//        Cuisine newCuisine = new Cuisine();
//        newCuisine.setName("Americana");
//        //ação
//        newCuisine = service.add(newCuisine);
//        //validação
//        assertThat(newCuisine).isNotNull();
//        assertThat(newCuisine.getId()).isNotNull();
//    }
//
//    @Test
//    void createdCuisineWithNameNull() {
//        Cuisine newCuisine = new Cuisine();
//        newCuisine.setName(null);
//
//        ConstraintViolationException error = Assertions.assertThrows(ConstraintViolationException.class, ()
//                -> service.add(newCuisine));
//
//        assertThat(error).isNotNull();
//    }
//
//    @Test
//    void deleteCuisineInUse() {
//        EntityNotFoundException error = Assertions.assertThrows(EntityNotFoundException.class, ()
//                -> service.delete(3L));
//        assertThat(error).isNotNull();
//    }
//
//    @Test
//    void deleteCuisineNotExistent() {
//        CuisineNotFoundException error = Assertions.assertThrows(CuisineNotFoundException.class, ()
//                -> service.delete(50L));
//        assertThat(error).isNotNull();
//    }

}
