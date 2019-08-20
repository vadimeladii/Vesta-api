package com.vesta.service.dto;

import lombok.Data;

@Data
public class AvatarDto {

    private Long id;
    private byte[] avatar;
    private String name;
    private Long userId;
}
