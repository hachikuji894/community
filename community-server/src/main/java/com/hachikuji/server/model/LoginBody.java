package com.hachikuji.server.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class LoginBody implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String code;

    private String uuid;

}
