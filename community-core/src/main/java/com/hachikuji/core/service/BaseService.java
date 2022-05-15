package com.hachikuji.core.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hachikuji.core.utils.StringUtils;
import com.hachikuji.core.domain.model.DataTable;
import com.hachikuji.core.domain.page.PageDomain;
import com.hachikuji.core.domain.page.TableSupport;
import com.hachikuji.core.utils.SqlUtils;

import java.util.List;

public class BaseService {

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtils.escapeOrderBySql(pageDomain.getOrderBy());
            Boolean reasonable = pageDomain.getReasonable();
            PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
        }
    }

    /**
     * 设置请求排序数据
     */
    protected void startOrderBy()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotEmpty(pageDomain.getOrderBy()))
        {
            String orderBy = SqlUtils.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.orderBy(orderBy);
        }
    }

    /**
     * 单表查询分页
     */
    protected DataTable getDataTablePage(List<?> list)
    {
        return getDataTablePage(list,new PageInfo(list).getTotal());
    }

    /**
     * 多表查询分页
     */
    protected DataTable getDataTablePage(List<?> list,long total)
    {
        DataTable dataTable = new DataTable();
        dataTable.setRows(list);
        dataTable.setTotal(total);
        return dataTable;
    }


}
