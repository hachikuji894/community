package com.hachikuji.core.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 表格对象，额外包含总行数
 */
@Data
@NoArgsConstructor
public class DataTable {
    /** 总记录数 */
    private long total;

    /** 列表数据 */
    private List<?> rows;
}
