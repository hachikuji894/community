package com.hachikuji.server.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoBody implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String headerUrl;

}
