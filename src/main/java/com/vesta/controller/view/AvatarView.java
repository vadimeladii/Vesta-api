package com.vesta.controller.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class AvatarView implements Serializable {

    private static final long serialVersionUID = -357965106000566400L;

    private Long id;
    private byte[] avatar;
    private String name;
}


