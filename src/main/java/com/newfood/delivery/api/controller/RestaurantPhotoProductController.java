package com.newfood.delivery.api.controller;

import com.newfood.delivery.dto.request.PhotoRequest;
import com.newfood.delivery.dto.response.PhotoResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/restaurant/{restaurantId}/product/{productId}/photo")
public class RestaurantPhotoProductController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PhotoResponse updatedPhoto(@PathVariable Long restaurantId, @PathVariable Long productId, @Valid PhotoRequest request) {
        return null;
    }

}
