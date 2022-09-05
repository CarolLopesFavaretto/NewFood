package com.newfood.delivery.domain.exceptions.handler;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Error {

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private LocalDateTime dateTime;
}
