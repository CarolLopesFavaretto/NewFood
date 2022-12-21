package com.newfood.delivery.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordRequest {

    private String currentPassword;
    private String newPassword;
}
