package com.newfood.delivery.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoResponse {

    private String fileName;
    private String description;
    private String contentType;
    private Long size;
}
