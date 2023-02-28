package com.newfood.delivery.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PhotoRequest {

    @NotNull
    private MultipartFile file;
    @NotNull
    private String description;
}
