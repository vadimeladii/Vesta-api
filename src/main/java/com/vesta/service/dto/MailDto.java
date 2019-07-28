package com.vesta.service.dto;

import lombok.Data;

import java.util.Map;

@Data
public class MailDto {

    private String from;
    private String to;
    private String subject;
    private String text;
    private Map<String, Object> model;

}
