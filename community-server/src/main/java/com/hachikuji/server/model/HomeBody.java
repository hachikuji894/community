package com.hachikuji.server.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class HomeBody implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String headerUrl;

    private String title;

    private LocalDateTime createTime;

    private Integer commentCount;

    private Double score;


}
