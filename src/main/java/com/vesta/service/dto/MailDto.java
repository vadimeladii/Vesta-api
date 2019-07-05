package com.vesta.service.dto;

import lombok.*;

import java.util.Map;

@Data
public class MailDto {
    private String from;
    private String to;
    private String subject;
    private Map<String, Object> model;

}
