package com.example.shopping.model;

import lombok.Data;

import java.util.List;

/**
 * @Description:分页结果类
 * @Author: SilenceOu
 * @Date: 2022/10/19 22:15
 */
@Data
public class PageResult<T> {
    private List<T> list;
    private Integer total;
}
