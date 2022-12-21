package com.newfood.delivery.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserRequest extends PasswordRequest {

    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;

}
