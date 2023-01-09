package com.newfood.delivery.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PermissionRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
