package com.newfood.delivery.api.exceptions.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class Error {

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private LocalDateTime dateTime;
    private String useMessage;
    private List<Field> fields;

    @Data
    @Builder
    public static class Field {

        private String name;
        private String userMessage;
    }
}
