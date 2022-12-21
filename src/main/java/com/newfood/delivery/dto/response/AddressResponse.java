package com.newfood.delivery.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressResponse {

    private String zipCode;
    private String publicPlace;
    private String number;
    private String complement;
    private String district;
    private CityResponse city;
}
