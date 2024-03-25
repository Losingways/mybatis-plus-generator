package com.test.common;


import com.test.util.GuavaUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 自定义请求基类
 *      1、公共分页参数 有默认值
 *      2、自定义排序字段处理
 */
public class BaseVO implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 当前页码
	 */
	private Integer pageNum=1;
	/**
	 * 每页记录数
	 */
    private Integer pageSize=20;
    /**
	 * 最大页面记录数
	 */
	public static int maxPageSize = 1000;

    /**
     * 排序字段(驼峰字段)
     */
    private String fieldSort;
    /**
     * 排序类型
     * 正序 asc
     * 倒序 desc
     */
    private String orderSort;

    /**
     * 获取排序字段【自动驼峰转数据库命名格式】
     * @return
     */
    public String getFieldSort() {
        if(StringUtils.isNotBlank(fieldSort)){
            return GuavaUtils.transformToDatabaseFieldLower(fieldSort);
        }
        return fieldSort;
    }

    public void setFieldSort(String fieldSort) {
        this.fieldSort = fieldSort;
    }

    public String getOrderSort() {
        if(StringUtils.isNotBlank(orderSort)){
            return orderSort.split("end")[0];
        }
        return orderSort;
    }


    public void setOrderSort(String orderSort) {
        this.orderSort = orderSort;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
