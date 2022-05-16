package com.hachikuji.server.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RegisterBody implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;

    private String username;

    private String password;


}