package com.newfood.delivery.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class PaymentIdRequest {

    @NotNull
    private Long id;
}
