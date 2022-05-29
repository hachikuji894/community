package com.hachikuji.server.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostInfoBody {

    /**
     * 帖子信息
     */
    private Integer discussPostId;

    private String title;

    private LocalDateTime createTime;

    private Integer commentCount;

    private Double score;

}
