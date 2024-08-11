package com.study.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;


//db용 객체
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Value
@Builder
public class CategoryVo {
    private int categoryId;
    private String categoryName;
}
