package com.hachikuji.server.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UserInfoBody implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String headerUrl;

    public UserInfoBody(String username, String headerUrl) {
        this.username = username;
        this.headerUrl = headerUrl;
    }

}
