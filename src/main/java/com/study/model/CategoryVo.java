package com.study.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;


//db용 객체
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Value
public class CategoryVo {
    private int categoryId;
    private String categoryName;
}
