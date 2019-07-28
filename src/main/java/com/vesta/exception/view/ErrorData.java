package com.vesta.exception.view;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ErrorData {
    private Date timestamp;
    private int status;
    private String error;
    private String message;
}
